package com.epam.testing.mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ResourceMock {

    public static String readResource(String resourceName) throws IOException {
        ClassLoader classLoader = ResourceMock.class.getClassLoader();
        if (classLoader != null) {
            InputStream resourceStream = classLoader.getResource(resourceName).openStream();
            return convertInputStreamToString(resourceStream);
        }

        return null;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}
