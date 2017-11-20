package midianet.friend.adm.domain;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

public interface Convertible {

    final Logger LOGGER = Logger.getLogger(Convertible.class);

    /**
     * Constroi uma lista de valores das propriedades de um Pojo
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Lista de valores
     */
    default List<Object> asListofValues(final String... fields) {
        return asListofValues(null, 0, null, fields);
    }

    /**
     * Constroi uma lista de valores das propriedades de um Pojo com um valor adicional
     * @param f função para geração do valor adicional
     * @param additionalValOrder ordem do valor adicional na lista
     * @param fieldForAdditionalVal atributo que terá seu valor usado como parâmetro da função de construção do valor adicional
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Lista de valores
     */
    default List<Object> asListofValues(final Function<Object, String> f, final Integer additionalValOrder, final String fieldForAdditionalVal, final String... fields) {
        final List<Object> values = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            try {
                if(additionalValOrder == i && f != null){
                    values.add(asObject(f.apply(getMethodValue(fieldForAdditionalVal))));
                }
                values.add(asObject(getMethodValue(fields[i])));
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
            } catch (InvocationTargetException e) {
                LOGGER.error(e);
            }
        }
        return values;
    }

    /**
     * Constroi um mapa com nome de atributos e seus respectivos valores
     * @param fields Atributos do pojo a que serão usados para construir um mapa lista de valores
     * @return Map com somente lista de campos
     */
    default Map<String, Object> asMapofValues(final String... fields) {
        return asMapofValues(null, null, null, fields);
    }

    /**
     * Constroi um mapa com nome de atributos e seus respectivos valores com um valor adicional
     * @param f função para geração do valor adicional
     * @param additionalField nome do campo adicional
     * @param fieldForAdditionalVal atributo que terá seu valor usado como parâmetro da função de construção do valor adicional
     * @param fields Atributos do pojo a que serão usados para construir a lista de valores
     * @return Map montado pelo processamento de função, campos adicionais e valor do campo adicional
     */
    default Map<String,Object> asMapofValues(final Function<Object, String> f, final String additionalField, final String fieldForAdditionalVal, final String... fields) {
        final Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {

            if (fields[i].split("-").length > 1) {
                map.put(fields[i],getNestedAttributtesAsString(fields[i], this, 0));
            } else {
                try {
                    if(f != null && !map.containsKey(additionalField)) {
                        map.put(additionalField, asObject(f.apply(getMethodValue(fieldForAdditionalVal))));
                    }
                    map.put(fields[i], asObject(getMethodValue(fields[i])));
                } catch (IllegalAccessException e) {
                    LOGGER.error(e);
                } catch (InvocationTargetException e) {
                    LOGGER.error(e);
                }
            }


        }
        return map;
    }

    default Object getNestedAttributtesAsString(final String field, final Object nestedObject, int i) {
        while (i < field.split("-").length ) {
            try {
                return asObject(getNestedAttributtesAsString(field,((Convertible)nestedObject).getMethodValue(field.split("-")[i]), ++i));
            } catch (InvocationTargetException e) {
                LOGGER.error(e);
            } catch (IllegalAccessException e) {
                LOGGER.error(e);
            } catch (Exception e) {
                return "";
            }
        }
        return asObject(nestedObject);
    }

    /**
     * Obtem o valor Literal do campo
     * @param fieldValue campo a ser convertido a String
     * @return representação em String do Objeto
     */
    default Object asObject(final Object fieldValue) {

        if(fieldValue == null){
            return "";
//        }else if (Date.class.isInstance(fieldValue)) {
//
//            final LocalDate ld = Try.of(() -> ((java.sql.Date) fieldValue).toLocalDate())
//                .recover(e -> ((Date) fieldValue).toInstant().atZone( ZoneId.systemDefault()).toLocalDate())
//                .get();
//
//            return ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return fieldValue;
        }
    }

    /**
     * Obtem o valor do método pelo nome
     * @param field campo que método get sera executado
     * @return valor do campo field
     * @throws InvocationTargetException para erros na execução do método
     * @throws IllegalAccessException caso não tenha acesso ao método (privado)
     */
    default Object getMethodValue(final String field) throws InvocationTargetException, IllegalAccessException {
//
//        final Boolean isBoolean = Try.of(() -> this.getClass().getDeclaredField(field))
//                .map(v -> Boolean.class.getSimpleName().equalsIgnoreCase(v.getType().getSimpleName())).recover(e -> false).get();

        final String format = getMethodFormat(false, field);
        final Method m = getMethod(format) == null? getMethod(getMethodFormat(false, field)) : getMethod(format);
        return m == null? null : m.invoke(this,null);
    }

    default String getMethodFormat(final boolean isBoolean, final String field){
        return (isBoolean?"is":"get").concat(String.valueOf(field.charAt(0)).toUpperCase()).concat(field.substring(1));
    }

    default Method getMethod(String format){
        return Arrays.asList(this.getClass().getDeclaredMethods()).stream().filter(m -> m.getName().equals(format)).findFirst().orElse(null);
    }

}