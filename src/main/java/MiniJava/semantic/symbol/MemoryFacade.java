package MiniJava.semantic.symbol;

import MiniJava.codeGenerator.Memory;

public class MemoryFacade {
    private final Memory memory;
    public MemoryFacade(Memory memory) {
        this.memory = memory;
    }

    public int getDateAddress() {
        int ret =  memory.getDateAddress();
        memory.incrementDataAddress();
        return ret;
    }
}
