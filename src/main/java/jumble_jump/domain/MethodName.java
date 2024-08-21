package jumble_jump.domain;

import java.util.ArrayList;
import java.util.List;

public class MethodName {
    private final List<String> methodNameEntry  = new ArrayList<>();
    private String camelTypeMethodName;

    public MethodName() {
    }

    public void addMethodNameEntry(String methodNameEntry) {
        this.methodNameEntry.add(methodNameEntry);
    }

    public void setCamelTypeMethodName(int index,String camelTypeToken) {
        methodNameEntry.set(index, camelTypeToken);
    }

    public List<String> getMethodNameEntry() {
        return methodNameEntry;
    }

    public void clearMethodNameEntry() {
        this.methodNameEntry.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String methodNameEntry : methodNameEntry) {
            stringBuilder.append(methodNameEntry);
        }
        return stringBuilder.toString();
    }

}
