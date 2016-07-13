package org.hcjf.encoding;

import org.hcjf.service.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author javaito
 * @mail javaito@gmail.com
 */
public final class EncodingService extends Service<EncodingImpl> {

    private static final String SERIALIZATION_SERVICE_NAME = "EncodingService";

    private static final EncodingService instance;

    static {
        instance = new EncodingService();
    }

    private final Map<MimeType, Map<String, EncodingImpl>> implementations;

    /**
     * Service constructor.
     */
    private EncodingService() {
        super(SERIALIZATION_SERVICE_NAME);
        implementations = new HashMap<>();
        registerConsumer(new JsonEncoding());
    }

    /**
     * This method register the consumer in the service.
     *
     * @param consumer Object with the logic to consume the service.
     * @throws RuntimeException It contains exceptions generated by
     *                          the particular logic of each implementation.
     */
    @Override
    public void registerConsumer(EncodingImpl consumer) {
        synchronized (this) {
            Map<String, EncodingImpl> mimeTypesImplementations =
                    implementations.get(consumer.getMimeType());
            if(mimeTypesImplementations == null) {
                mimeTypesImplementations = new HashMap<>();
                implementations.put(consumer.getMimeType(), mimeTypesImplementations);
            }
            mimeTypesImplementations.put(consumer.getImplementationName(), consumer);
        }
    }

    /**
     *
     * @param mimeType
     * @param impl
     */
    private EncodingImpl getSerializationImpl(MimeType mimeType, String impl) {
        EncodingImpl encoding = null;
        if(instance.implementations.containsKey(mimeType)) {
            encoding = instance.implementations.get(mimeType).get(impl);
        }

        if(encoding == null) {
            throw new IllegalArgumentException("EncodingService implementation not found: " + mimeType + "@" + impl);
        }

        return encoding;
    }

    /**
     *
     * @param mimeType
     * @param impl
     * @param decodedPackage
     * @return
     */
    public static byte[] encode(MimeType mimeType, String impl, DecodedPackage decodedPackage) {
        return instance.getSerializationImpl(mimeType, impl).encode(decodedPackage);
    }

    /**
     *
     * @param mimeType
     * @param impl
     * @param objectClass
     * @param data
     * @param parameters
     * @return
     */
    public static DecodedPackage decode(MimeType mimeType, String impl, Class objectClass, byte[] data, Map<String, Object> parameters) {
        return instance.getSerializationImpl(mimeType, impl).decode(objectClass, data, parameters);
    }

    /**
     *
     * @param mimeType
     * @param impl
     * @param data
     * @param parameters
     * @return
     */
    public static DecodedPackage decode(MimeType mimeType, String impl, byte[] data, Map<String, Object> parameters) {
        return instance.getSerializationImpl(mimeType, impl).decode(data, parameters);
    }
}
