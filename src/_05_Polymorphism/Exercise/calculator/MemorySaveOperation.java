package _05_Polymorphism.Exercise.calculator;

public class MemorySaveOperation implements Operation {
    private int operand;
    private boolean completed;
    private Memory memory;

    public MemorySaveOperation(Memory memory) {

        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        this.operand = operand;
        memory.save(operand);
        this.completed = true;
    }

    @Override
    public int getResult() {
        return this.operand;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
