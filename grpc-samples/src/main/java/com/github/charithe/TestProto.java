package com.github.charithe;

import com.github.charithe.grpc.GrpcSamples;
import com.google.protobuf.Descriptors;

public class TestProto {

    public static void main(String[] args){
        Descriptors.Descriptor descriptor = GrpcSamples.TestMessage3.getDescriptor();
        /*GrpcSamples.TestMessage3 msg1 = GrpcSamples.TestMessage3.getDefaultInstance();
        Descriptors.Descriptor descriptor = msg1.getDescriptorForType();*/

        for(Descriptors.FieldDescriptor fd : descriptor.getFields()){
            System.out.println(fd.getName());
            System.out.println(fd.getIndex());
            System.out.println(fd.getJavaType().name());
            System.out.println(fd.isExtension());
            System.out.println(fd.isMapField());
            System.out.println(fd.isOptional());
            System.out.println(fd.isRepeated());
            System.out.println(fd.isRequired());
            System.out.println(fd.getContainingOneof());
            System.out.println("-------");
        }
    }
}
