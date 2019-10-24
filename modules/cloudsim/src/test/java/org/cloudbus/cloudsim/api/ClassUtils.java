package org.cloudbus.cloudsim.api;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClassUtils {

    // 给一个接口，返回这个接口的所有实现类
    public static List getAllClassByInterface(Class<?> c) {
        List<String> returnClassList = Lists.newArrayListWithExpectedSize(10); // 返回结果
        // 如果不是一个接口，则不做处理
        if (c.isInterface()) {
            String packageName = c.getPackage().getName(); // 获得当前的包名
            try {
                List<Class<?>> allClass = getClasses(packageName); // 获得当前包下以及子包下的所有类
                // 判断是否是同一个接口
                for (Class<?> clazz : allClass) {
                    if (!c.isAssignableFrom(clazz) || c.equals(clazz) || Modifier.isAbstract(clazz.getModifiers()))
                        continue;
                    returnClassList.add(clazz.getName());
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return returnClassList;
    }

    // 从一个包中查找出所有的类，在jar包中不能查找
    private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = Lists.newArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = Lists.newArrayList();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = Lists.newArrayListWithExpectedSize(2);
        File[] files;
        if (!directory.exists() || null == (files = directory.listFiles())) {
            return classes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * 根据属性名获取属性值
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            if (null == method)
                return null;
            return method.invoke(o, new Object[]{});
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        }
        return null;
    }

    private static List<String> getFiledName(Object o) {
        return getFields(o).stream().map(Field::getName).collect(Collectors.toList());
    }

    public static List<Field> getFields(Object o) {
        return getFields(o.getClass());
    }

    public static List<Field> getFields(Class clazz) {
        List<Field> fields = Lists.newArrayList();
        while (clazz != null && !clazz.equals(Class.class)) {
            Field[] field = clazz.getDeclaredFields();
            fields.addAll(Arrays.asList(field));
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private static List getFiledsInfo(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        List list = new ArrayList();
        Map infoMap = null;
        for (int i = 0; i < fields.length; i++) {
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     */
    @Deprecated
    public static List<Object> getFiledValues(Object o) {
        List<String> fieldNames = getFiledName(o);
        List<Object> values = Lists.newArrayList();
        for (int i = 0; i < fieldNames.size(); i++) {
            Object value = getFieldValueByName(fieldNames.get(i), o);
            if (null != value)
                values.add(value);
        }
        return values;
    }

    public static List<Object> getFiledValue(Object o) {
        List<Field> fields = getFields(o);
        for (Field field : fields) {
            field.setAccessible(true);
        }
        List<Object> values = Lists.newArrayList();
        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers()))
                continue;
            try {
                Object value = field.get(o);
                if (null == value || value instanceof Boolean || value instanceof Map
                        || (value instanceof String && StringUtils.isEmpty((String) value)))
                    continue;
                if (value instanceof Collection) {
                    Iterator iterator = ((Collection) value).iterator();
                    while (iterator.hasNext()) {
                        Object object = iterator.next();
                        if (null == object || object instanceof Boolean || object instanceof Collection || object instanceof Map)
                            continue;
                        values.add(object);

                    }
                } else {
                    values.add(value);
                }
            } catch (IllegalAccessException e) {
            }
        }
        return values;
    }

    public static int getIntersection(List<Object> conValues, List<Object> objectValues) {
        if (conValues.isEmpty() || objectValues.isEmpty())
            return 0;
        int count = 0;
        for (int i = 0; i < conValues.size(); i++) {
            Object object = conValues.get(i);
            if (objectValues.contains(object)) {
                count++;
            }
        }
        return count;
    }


    /**
     * 获取同一路径下所有子类或接口实现类
     *
     * @param cls
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws IOException,
            ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (Class<?> c : getClasses(cls)) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * 取得当前类路径下的所有类
     *
     * @param cls
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClasses(Class<?> cls) throws IOException,
            ClassNotFoundException {
        String pk = cls.getPackage().getName();
        String path = pk.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), pk);
    }

    public static List<Class> getClasses(String prefix, Class cls) {
        Reflections reflections = new Reflections(prefix);

        return Lists.newArrayList(reflections.getSubTypesOf(cls));
    }

    /**
     * 迭代查找类
     *
     * @param dir
     * @param pk
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        File[] files;
        if (!dir.exists() || null == (files = dir.listFiles())) {
            return classes;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                classes.addAll(getClasses(f, pk + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                classes.add(Class.forName(pk + "." + name.substring(0, name.length() - 6)));
            }
        }
        return classes;
    }

    /**
     * 获得一个类的成员变量Class
     *
     * @param clazz 类
     * @return 成员变量
     */
    public static Map<String, Class<?>> getDeclaredFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Class<?>> classes = new LinkedHashMap<>();
        Arrays.stream(fields).forEach(field -> {
            if (Modifier.isAbstract(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                return;
            }
            classes.put(field.getName(), field.getType());
        });

        return classes;
    }

    /**
     * 获得一个类的构造函数（参数为SimpleName）
     *
     * @param clazz 类
     * @return 构造函数列表
     */
    public static List<String> getClassConstructs(Class clazz) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        List<String> constructs = Lists.newArrayListWithCapacity(constructors.length);
        for (Constructor constructor : constructors) {
            Class[] paClass = constructor.getParameterTypes();
            String name = constructor.getName();
            int i = 0;
            StringBuilder params = new StringBuilder();
            for (Class param : paClass) {
                if (0 != i) {
                    params.append(", ");
                }
                params.append(param.getSimpleName());
                i++;
            }
            constructs.add(name + "(" + params.toString() + ")");
        }
        return constructs;
    }

    /**
     * 获得一个类的成员方法（简单描述，忽略类型）
     *
     * @param clazz 类
     * @return 成员方法
     */
    public static List<String> getDeclaredMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<String> methodString = Lists.newArrayListWithCapacity(methods.length);
        for (Method method : methods) {
            Class[] paClass = method.getParameterTypes();
            String name = method.getName();
            int i = 0;
            StringBuilder params = new StringBuilder();
            for (Class param : paClass) {
                if (0 != i) {
                    params.append(", ");
                }
                params.append(param.getSimpleName());
                i++;
            }
            methodString.add(name + "(" + params.toString() + ")");
        }
        return methodString;
    }

    public static String getClassAnnotation(Class<?> clazz) {

        String clazzPath = findFilePath(clazz);
        List<String> result = Lists.newArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(clazzPath)), "UTF-8"));

            String str = null;
            Pattern pattern = Pattern.compile("^(/+|\\*+)\\**/*");
            while (null != (str = br.readLine())) {
                if (str.startsWith("public")) {
                    break;
                }
                str = str.trim();
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    str = str.replace(matcher.group(), "").replace("relation", "").replace("data", "")
                            .trim().replace("<p/>", "").replace("<p>", "");
                    if (StringUtils.isEmpty(str) || str.contains("Created") || str.contains("created")
                            || str.startsWith("@") || str.contains("author") || str.contains("Modified")
                            || str.contains("modified") || str.startsWith("201")) {
                        continue;
                    }
                    result.add(str);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Joiner.on(",").skipNulls().join(result);
    }

    private static String findFilePath(Class<?> clazz) {
        String clazzPath = clazz.getResource(clazz.getSimpleName() + ".class").getFile();
        clazzPath = clazzPath.replace("target/classes", "src/main/java").replace(".class", ".java");

        return clazzPath;
    }

    public static String getPointAnnotation(String clazzPath, String regex) {
        List<String> result = Lists.newArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(clazzPath)), "UTF-8"));

            String str;
            Pattern pattern = Pattern.compile("^(/+|\\*+)\\**/*");
            while (null != (str = br.readLine())) {
                if (RegexConstant.hasPattern(regex, str)) {
                    break;
                }
                if (str.contains("{") || RegexConstant.hasPattern("(public|private|protect)", str)) {
                    result.clear();
                }
                str = str.trim();
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    str = str.replace(matcher.group(), "").replace("relation", "").replace("data", "")
                            .trim().replace("<p/>", "").replace("<p>", "");
                    if (StringUtils.isEmpty(str) || str.contains("Created")
                            || str.startsWith("@") || str.contains("author") || str.contains("Modified")
                            || str.contains("modified") || str.startsWith("201")) {
                        continue;
                    }
                    if (StringUtils.isEmpty(str) || str.startsWith("Log")) {
                        continue;
                    }
                    result.add(str);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Joiner.on(",").skipNulls().join(result);
    }

    public static String getPointParameter(String clazzPath, String regex) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(clazzPath)), "UTF-8"));

            String str;
            while (null != (str = br.readLine())) {
                if (RegexConstant.hasPattern(regex, str)) {
                    Pattern p = Pattern.compile("\\(.*\\)");
                    Matcher m = p.matcher(str);
                    if (m.find()) {
                        return m.group(0).substring(1, m.group().length() - 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String getPointAnnotation(Class<?> clazz, Field field) {
        String regex = String.format(".*%s.*%s", field.getType().getSimpleName(), field.getName());

        return getPointAnnotation(findFilePath(clazz), regex);
    }

    public static String getPointAnnotation(Class<?> clazz, Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> types = Arrays.stream(parameters).map(parameter -> parameter.getType().getSimpleName().replace("[]", "") + ".*").collect(Collectors.toList());
        String regex = String.format(".*%s.*%s.*%s.*%s", getModifier(method), method.getReturnType().getSimpleName(), method.getName(), Joiner.on("").join(types));

        return getPointAnnotation(findFilePath(clazz), regex);
    }

    private static String getModifier(Method method) {
        if (Modifier.isPublic(method.getModifiers())) {
            return "public";
        } else if (Modifier.isPrivate(method.getModifiers())) {
            return "private";
        } else {
            return "protect";
        }
    }

    public static String getParameter(Class<?> clazz, Field field) {
        String regex = String.format(".*%s.*%s", field.getType().getSimpleName(), field.getName());
        return getPointParameter(findFilePath(clazz), regex);
    }

    public static String getParameter(Class<?> clazz, Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> types = Arrays.stream(parameters).map(parameter -> parameter.getType().getSimpleName().replace("[]", "") + ".*").collect(Collectors.toList());
        String regex = String.format(".*%s.*%s.*%s.*%s.*", getModifier(method), method.getReturnType().getSimpleName(), method.getName(), Joiner.on("").join(types));

        return getPointParameter(findFilePath(clazz), regex);
    }

}
