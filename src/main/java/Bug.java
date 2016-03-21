import com.google.inject.TypeLiteral;

import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class Bug {

    public static void main(String[] args) {

        new Bug().run();
    }

    private void run() {
        final ParameterDescriptor<Map<String, String>, Map<String, Pattern>> pathsParam;

        //noinspection unchecked
        pathsParam = ParameterDescriptor.type("paths",
                                              (Class<? extends Map<String, String>>) new TypeLiteral<Map<String, String>>() {}.getRawType(),
                                              (Class<? extends Map<String, Pattern>>) new TypeLiteral<Map<String, Pattern>>() {}.getRawType())
// using the next two lines instead make the problem go away.
//                                              (Class<Map<String, String>>) new TypeLiteral<Map<String, String>>() {}.getRawType(),
//                                              (Class<Map<String, Pattern>>) new TypeLiteral<Map<String, Pattern>>() {}.getRawType())
                .transform(inputMap -> inputMap.entrySet().stream()
                        .collect(toMap(Map.Entry::getKey, e -> Pattern.compile(e.getValue()))))
                .build();
    }

}
