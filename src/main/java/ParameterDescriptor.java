import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;
import java.util.Optional;

@AutoValue
public abstract class ParameterDescriptor<T, R> {

    public abstract Class<? extends T> type();

    public abstract Class<? extends R> transformedType();

    public abstract String name();

    public abstract boolean optional();

    public abstract java.util.function.Function<T, R> transform();

    public static <T,R> Builder<T, R> param() {
        return new AutoValue_ParameterDescriptor.Builder<T, R>().optional(false);
    }

    public static Builder<String, String> string(String name) {
        return string(name, String.class);
    }

    public static <R> Builder<String, R> string(String name, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<String, R>param().type(String.class).transformedType(transformedClass).name(name);
    }

    public static Builder<Object, Object> object(String name) {
        return object(name, Object.class);
    }

    public static <R> Builder<Object, R> object(String name, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<Object, R>param().type(Object.class).transformedType(transformedClass).name(name);
    }

    public static Builder<Long, Long> integer(String name) {
        return integer(name, Long.class);
    }

    public static <R> Builder<Long, R> integer(String name, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<Long, R>param().type(Long.class).transformedType(transformedClass).name(name);
    }

    public static Builder<Double, Double> floating(String name) {
        return floating(name, Double.class);
    }
    public static <R> Builder<Double, R> floating(String name, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<Double, R>param().type(Double.class).transformedType(transformedClass).name(name);
    }

    public static Builder<Boolean, Boolean> bool(String name) {
        return bool(name, Boolean.class);
    }

    public static <R> Builder<Boolean, R> bool(String name, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<Boolean, R>param().type(Boolean.class).transformedType(transformedClass).name(name);
    }

    public static <T> Builder<T, T> type(String name, Class<? extends T> typeClass) {
        return type(name, typeClass, typeClass);
    }

    public static <T, R> Builder<T, R> type(String name, Class<? extends T> typeClass, Class<? extends R> transformedClass) {
        return ParameterDescriptor.<T, R>param().type(typeClass).transformedType(transformedClass).name(name);
    }

    @Nullable
    public R required(Object args, Object context) {
//        final Object precomputedValue = args.getPreComputedValue(name());
//        if (precomputedValue != null) {
//            return transformedType().cast(precomputedValue);
//        }
//        final Expression valueExpr = args.expression(name());
//        if (valueExpr == null) {
//            return null;
//        }
        final Object value = null;
        return transformedType().cast(transform().apply(type().cast(value)));
    }

    public Optional<R> optional(Object args, Object context) {
        return Optional.of(required(args, context));
    }

    @AutoValue.Builder
    public static abstract class Builder<T, R> {
        public abstract Builder<T, R> type(Class<? extends T> type);
        public abstract Builder<T, R> transformedType(Class<? extends R> type);
        public abstract Builder<T, R> name(String name);
        public abstract Builder<T, R> optional(boolean optional);

        public Builder<T, R> optional() {
            return optional(true);
        }

        abstract ParameterDescriptor<T, R> autoBuild();
        public ParameterDescriptor<T, R> build() {
            try {
                transform();
            } catch (IllegalStateException ignored) {
                // unfortunately there's no "hasTransform" method in autovalue
                //noinspection unchecked
                transform((java.util.function.Function<T, R>) java.util.function.Function.<T>identity());
            }
            return autoBuild();
        }

        public abstract Builder<T, R> transform(@Nullable java.util.function.Function<T, R> transform);
        @Nullable
        public abstract java.util.function.Function<T, R> transform();

    }
}
