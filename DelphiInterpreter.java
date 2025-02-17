import java.util.*;

public class DelphiInterpreter extends DelphiBaseVisitor<Object> {
    private final Map<String, ClassDef> classTable = new HashMap<>();
    private final Map<String, InterfaceDef> interfaceTable = new HashMap<>();
    private final Map<String, ObjectInstance> objectTable = new HashMap<>();

    // Visit class declarations
    @Override
    public Object visitClassType(DelphiParser.ClassTypeContext ctx) {
        String className = ctx.identifier(0).getText();
        ClassDef newClass = new ClassDef(className);

        // Handle Inheritance
        if (ctx.identifier().size() > 1) { // Check if parent class exists
            String parentClass = ctx.identifier(1).getText();
            if (!classTable.containsKey(parentClass)) {
                System.err.println("Error: Parent class " + parentClass + " not found.");
                return null;
            }
            newClass.parentClass = parentClass;
        }

        classTable.put(className, newClass);
        System.out.println("Defined class: " + className);
        return null;
    }


    // Visit interface declarations
    public Object visitInterfaceType(DelphiParser.InterfaceTypeContext ctx) {
        String interfaceName = ctx.getChild(1).getText(); // FIXED
        InterfaceDef newInterface = new InterfaceDef(interfaceName);
        interfaceTable.put(interfaceName, newInterface);
        System.out.println("Defined interface: " + interfaceName);
        return null;
    }

    public Object visitConstructorDeclaration(DelphiParser.ConstructorDeclarationContext ctx) {
        String className = ctx.getChild(1).getText();
        if (classTable.containsKey(className)) {
            classTable.get(className).hasConstructor = true;
            System.out.println("Class " + className + " has a constructor.");
        }
        return null;
    }

    public Object visitDestructorDeclaration(DelphiParser.DestructorDeclarationContext ctx) {
        String className = ctx.getChild(1).getText();
        if (classTable.containsKey(className)) {
            classTable.get(className).hasDestructor = true;
            System.out.println("Class " + className + " has a destructor.");
        }
        return null;
    }
}

// Class Definition Storage
class ClassDef {
    String name;
    String parentClass = null;
    boolean hasConstructor = false;
    boolean hasDestructor = false;

    public ClassDef(String name) {
        this.name = name;
    }
}

// Interface Definition Storage
class InterfaceDef {
    String name;

    public InterfaceDef(String name) {
        this.name = name;
    }
}

// Object Storage
class ObjectInstance {
    String className;

    public ObjectInstance(String className) {
        this.className = className;
    }
}
