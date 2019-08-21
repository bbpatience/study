package com.walle.asm;

import java.io.FileInputStream;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;


/**
 * @author: bbpatience
 * @date: 2019/8/7
 * @description: AsmVerify
 **/
public class AsmVerify {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/bbpatience/development/code/dream/code/study/src/main/java/com/walle/asm/ByteCodeDemo.class");
        ClassReader classReader = new ClassReader(fileInputStream);
        ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);


        //CORE API
        //Java8选择ASM5，
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, cw) {
//            @Override
//            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
//                System.out.println("field:" + name);
//                return super.visitField(access, name, desc, signature, value);
//            }
//
//            @Override
//            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//                System.out.println("方法" + name);
//                return super.visitMethod(access, name, desc, signature, exceptions);
//            }
        };
        //忽略调试信息
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);



        //TREE API
        ClassNode classNode = new ClassNode(Opcodes.ASM5);
        classReader.accept(classNode, ClassReader.SKIP_DEBUG);
        for (MethodNode methodNode:classNode.methods) {
            System.out.println(methodNode.name);
        }
        classNode.accept(cw);

    }
}
