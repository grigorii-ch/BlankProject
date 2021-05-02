package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;
import com.chuprynin.epam.rd.exceptions.NoValueAnnotationException;
import com.chuprynin.epam.rd.helper.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Класс для работы с pojo
 * Валидирует наличие аннотаций Entity, Value
 * Заполняет поля pojo в соответствии с правилами,
 * если есть аннотации Entity у класса, то при наличии Value на поле,
 * заполнит его значение при соответствии типов,
 * иначе будет передано дефолтное значение
 */
public class Process {

    private static final Logger logger = LoggerFactory.getLogger(Process.class);
    /**
     * Переменная для хранеия примитивов и оберток
     */
    private final Map<Class<?>, Class<?>> map = new HashMap<>();

    /**
     * Конструктор, заполняет мапу map
     */
    public Process() {
        map.put(byte.class, Byte.class);
        map.put(Byte.class, Byte.class);
        map.put(short.class, Short.class);
        map.put(Short.class, Short.class);
        map.put(int.class, Integer.class);
        map.put(Integer.class, Integer.class);
        map.put(long.class, Long.class);
        map.put(Long.class, Long.class);
        map.put(float.class, Float.class);
        map.put(Float.class, Float.class);
        map.put(double.class, Double.class);
        map.put(Double.class, Double.class);
        map.put(char.class, Character.class);
        map.put(Character.class, Character.class);
        map.put(boolean.class, Boolean.class);
        map.put(Boolean.class, Boolean.class);
    }

    /**
     * Основной метод работы класса
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Object run(Object object, Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object returnObject = object;
        if (loockAnnotations(aClass)) {
            returnObject = fillVariables(aClass, object);
        }
        logger.info("Возвращаемый объект: {}", returnObject.toString());
        return returnObject;
    }

    /**
     * Метод заполнения полей классов aClass в соответствии с правилами.
     *
     * @param aClass - класс объекта, в котором будут проверяться аннотации
     * @param object - экземпляр объекта в который будут слетаться данные из аннотации
     * @return - возвращается заполненный экземпляр объекта (если аннотации заполнены в соответствии с правилами)
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private Object fillVariables(Class<?> aClass, Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<AccessibleObject> fieldList = valueFieldAnnotations(aClass);
        List<AccessibleObject> methodList = valueMethodAnnotations(aClass);
        logger.debug("Обрабатывается {} полей", fieldList.size());
        logger.debug("Обрабатывается {} методов", methodList.size());
        String fileDataAnnotation = FileHelper.getDataFromFile();

        fillFiledValues(object, fieldList, fileDataAnnotation);

        fillMethodValues(object, methodList, fileDataAnnotation);
        return object;
    }

    /**
     * Метод заполнения полей класса , если установлена аннотация Value
     * если в аннотации указан параметр path значения будут подтянуты из файла
     *
     * @param object             - объект класса
     * @param methodList         - список методов
     * @param fileDataAnnotation - значения для запыления из файла
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private void fillMethodValues(Object object, List<AccessibleObject> methodList, String fileDataAnnotation) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (AccessibleObject method : methodList) {
            String patchValue = method.getAnnotation(Value.class).path();

            Method objectMethod = (Method) method;
            Class<?> objTypeClass = objectMethod.getParameterTypes()[0];
            logger.debug("Обрабатывается метод {}", objectMethod.getName());
            if (!patchValue.trim().isEmpty()) {
                int dataPosition = 0;
                if (patchValue.equals("age")) {
                    dataPosition = 1;
                }
                try {
                    String[] fileData = fileDataAnnotation.split(",");
                    String dataName = fileData[dataPosition].split("=")[0];
                    String dataValue = fileData[dataPosition].split("=")[1];
                    if (dataName.equals(patchValue)) {
                        parseValue(object, objectMethod, dataValue, objTypeClass);
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.warn("Ошибка при заполнении данных из файла, данные будут заполненны из аннотации");
                }
            }
            String annotationValue = method.getAnnotation(Value.class).value();
            logger.debug("Заполнение из аннотации");
            logger.debug("типа поля = {}", objectMethod.getParameterTypes()[0]);
            parseValue(object, objectMethod, annotationValue, objTypeClass);
        }
    }

    /**
     * Метод производит преобразование переданного значения к типу переданного поля.
     * по переданному типу класса classType
     * получает из map класс обертку, у которой вызывается метод valueOf
     * преобразованные данные передаются в поле объекта
     *
     * @param object          - объект
     * @param objectField     - поле
     * @param annotationValue -значение аннотации
     * @param classType       - типа класса
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void parseValue(Object object, Field objectField, String annotationValue, Class<?> classType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> wrapperClass = map.get(classType);
        objectField.setAccessible(true);
        if (wrapperClass == null) {
            objectField.set(object, annotationValue);
            return;
        }

        Method method = wrapperClass.getDeclaredMethod("valueOf", String.class);
        method.setAccessible(true);

        try {
            objectField.set(object, method.invoke(null, annotationValue));
        } catch (Exception e) {
            if (wrapperClass == Boolean.class) {
                objectField.set(object, method.invoke(null, false));
            } else {
                objectField.set(object, method.invoke(null, getDefauiltValue()));
            }
        }
    }

    /**
     * Метод производит преобразование переданного значения к типу переданного поля.
     * по переданному типу класса classType
     * получает из map класс обертку, у которой в вызывается метод valueOf
     * преобразованные данные передаются в метод объекта
     *
     * @param object          - объект
     * @param objectMethod    - метод
     * @param annotationValue -значение аннотации
     * @param classType       - типа класса
     */
    private void parseValue(Object object, Method objectMethod, String annotationValue, Class<?> classType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> wrapperClass = map.get(classType);
        if (wrapperClass == null) {
            objectMethod.invoke(object, annotationValue);
            return;
        }
        Method method = wrapperClass.getDeclaredMethod("valueOf", String.class);
        method.setAccessible(true);
        objectMethod.setAccessible(true);
        try {
            objectMethod.invoke(object, method.invoke(null, annotationValue));
        } catch (Exception e) {
            if (wrapperClass == Boolean.class) {
                objectMethod.invoke(object, method.invoke(null, false));
            } else {
                objectMethod.invoke(object, method.invoke(null, getDefauiltValue()));
            }
        }
    }

    /**
     * Метод заполнения полей класса через методы, если установлена аннотация Value
     * если в аннотации указан параметр path значения будут подтянуты из файла
     *
     * @param object             - объект класса
     * @param fieldList          - список методов
     * @param fileDataAnnotation - значения для запыления из файла
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void fillFiledValues(Object object, List<AccessibleObject> fieldList, String fileDataAnnotation) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (AccessibleObject field : fieldList) {
            Field objectField = (Field) field;
            logger.debug("Обрабатывается поле {}", objectField.getName());
            String patchValue = field.getAnnotation(Value.class).path();

            if (!patchValue.trim().isEmpty()) {
                int dataPosition = 0;
                if (patchValue.equals("age")) {
                    dataPosition = 1;
                }
                try {
                    String[] fileData = fileDataAnnotation.split(",");
                    String dataName = fileData[dataPosition].split("=")[0];
                    String dataValue = fileData[dataPosition].split("=")[1];
                    if (dataName.equals(patchValue)) {
                        logger.debug("Заполнение из файла");
                        parseValue(object, objectField, dataValue, objectField.getType());
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.warn("Ошибка при заполнении данных из файла, данные будут заполненны из аннотации");
                }
            }
            String annotationValue = field.getAnnotation(Value.class).value();
            logger.debug("Заполнение из аннотации");
            parseValue(object, objectField, annotationValue, objectField.getType());
        }
    }

    /**
     * Метод для получения дефолтного значения аннотации Value
     *
     * @return дефолтное значение аннотации Value
     * @throws NoSuchMethodException - если у аннотации отсутствует параметр value
     */
    private String getDefauiltValue() throws NoSuchMethodException {
        Class<?> clazz = Value.class;
        Method method = clazz.getDeclaredMethod("value");
        return (String) method.getDefaultValue();
    }

    /**
     * Метод вызова проверки и нотификации о результатах проверки класса объекта
     *
     * @param aClass - класс объекта, в котором будут проверяться аннотации
     * @return boolean true/false в зависимости от проверки по правилам
     */
    private boolean loockAnnotations(Class<?> aClass) {
        boolean isAnnotationValid = false;
        try {
            isAnnotationValid = walkOnAnnotations(aClass);
            notifyAfter(isAnnotationValid, aClass.getName());
            return isAnnotationValid;
        } catch (IllegalStateException | NoValueAnnotationException e) {
            logger.warn("Ошибка {}", e.getMessage(), e);
        }
        return isAnnotationValid;
    }

    /**
     * Метод проверяет аннотации класса согласно правилу
     *
     * @param pojoClass класс объекта, в котором будут проверяться аннотации
     * @return - true/false, результат зависит от правил
     * @throws NoValueAnnotationException - если есть аннотация Entity и нет Value
     * @throws IllegalStateException      - если нет аннотация Entity и есть Value
     */
    private boolean walkOnAnnotations(Class<?> pojoClass)
            throws NoValueAnnotationException, IllegalStateException {
        List<AccessibleObject> fieldsWithAnotation = valueFieldAnnotations(pojoClass);
        List<AccessibleObject> methodsWithAnotation = valueMethodAnnotations(pojoClass);
        boolean isValue = valueAnnotationIsPresend(fieldsWithAnotation.size(), methodsWithAnotation.size());
        if (isEntityAnnotation(pojoClass)) {
            if (!isValue) {
                throw new NoValueAnnotationException(String.format("У сущности %s при наличии аннотации Entity " +
                        "отсутствует аннотация Value", pojoClass.getName()));
            }
        } else {
            if (isValue) {
                throw new IllegalStateException(String.format("У сущности %s при отсутствии аннотации Entity " +
                        "присутствуют аннотации Value", pojoClass.getName()));
            }
        }
        return true;
    }

    /**
     * Метод проверки наличия аннотации Value у полей и методов класса
     * на основе их количества
     *
     * @param fieldCount  - int поля класса с аннотацией Value
     * @param methodCount - int методы класса с аннотацией Value
     * @return true/false, зависит от входных переменных
     */
    boolean valueAnnotationIsPresend(int fieldCount, int methodCount) {
        return !((fieldCount == 0) & (methodCount == 0));
    }

    /**
     * Проверка наличия аннотации Entity у переданного класса aClass
     *
     * @param aClass - класс объекта.
     * @return true если есть, иначе false
     */
    private boolean isEntityAnnotation(Class<?> aClass) {
        return Objects.nonNull(aClass.getAnnotation(Entity.class));
    }

    /**
     * Определение набора полей у aClass, отсеивание полей не имеющих аннотации Value
     *
     * @param aClass - класс объекта.
     * @return list полей класса
     */
    private List<AccessibleObject> valueFieldAnnotations(Class<?> aClass) {
        List<AccessibleObject> list = new ArrayList<>(Arrays.asList(aClass.getDeclaredFields()));
        return removeWOAnnotationElement(list);
    }

    /**
     * Определение набора методой у aClass, отсеивание полей не имеющих аннотации Value
     *
     * @param aClass - класс объекта.
     * @return list методов класса
     */
    private List<AccessibleObject> valueMethodAnnotations(Class<?> aClass) {
        List<AccessibleObject> list = new ArrayList<>(Arrays.asList(aClass.getMethods()));
        return removeWOAnnotationElement(list);
    }

    /**
     * отсеивание полей не имеющих аннотации Value
     *
     * @param list - list AccessibleObject (поля/методы) класса
     * @return list  AccessibleObject - у которых есть аннотации Value
     */
    private List<AccessibleObject> removeWOAnnotationElement(List<AccessibleObject> list) {
        List<AccessibleObject> clearList = new ArrayList<>();
        for (AccessibleObject object : list) {
            if (Objects.nonNull(object.getAnnotation(Value.class))) {
                clearList.add(object);
            }
        }
        return clearList;
    }

    /**
     * метод для нотификации. результатов проверки аннотаций, в соответствии с правилами.
     *
     * @param isValid - результат проверки
     * @param name    - имя класса
     */
    private void notifyAfter(boolean isValid, String name) {
        if (isValid) {
            logger.info("Pojo объект {}, имеет верную структуру аннотаций", name);
        }
    }
}