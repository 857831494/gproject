// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Status.proto

package com.gproject.dto.proto;

public final class StatusDTO {
  private StatusDTO() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code gsp.StatusCode}
   */
  public enum StatusCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>idle = 1;</code>
     */
    idle(0, 1),
    /**
     * <code>fight = 2;</code>
     */
    fight(1, 2),
    /**
     * <code>busy = 3;</code>
     */
    busy(2, 3),
    ;

    /**
     * <code>idle = 1;</code>
     */
    public static final int idle_VALUE = 1;
    /**
     * <code>fight = 2;</code>
     */
    public static final int fight_VALUE = 2;
    /**
     * <code>busy = 3;</code>
     */
    public static final int busy_VALUE = 3;


    public final int getNumber() { return value; }

    public static StatusCode valueOf(int value) {
      switch (value) {
        case 1: return idle;
        case 2: return fight;
        case 3: return busy;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<StatusCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<StatusCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<StatusCode>() {
            public StatusCode findValueByNumber(int number) {
              return StatusCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.gproject.dto.proto.StatusDTO.getDescriptor().getEnumTypes().get(0);
    }

    private static final StatusCode[] VALUES = values();

    public static StatusCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private StatusCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:gsp.StatusCode)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Status.proto\022\003gsp*+\n\nStatusCode\022\010\n\004idl" +
      "e\020\001\022\t\n\005fight\020\002\022\010\n\004busy\020\003B#\n\026com.gproject" +
      ".dto.protoB\tStatusDTO"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
