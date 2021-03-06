// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommandCode.proto

package com.gproject.common.dto.proto;

public final class CommandCodeDTO {
  private CommandCodeDTO() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code gsp.CommandCode}
   */
  public enum CommandCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>C2SLoginCode = 1;</code>
     */
    C2SLoginCode(0, 1),
    /**
     * <code>C2SHeart = 2;</code>
     */
    C2SHeart(1, 2),
    /**
     * <code>C2SEnterWorld = 3;</code>
     *
     * <pre>
     *进入游戏服
     * </pre>
     */
    C2SEnterWorld(2, 3),
    /**
     * <code>C2SCreateRoleCode = 4;</code>
     *
     * <pre>
     *创建角色
     * </pre>
     */
    C2SCreateRoleCode(3, 4),
    /**
     * <code>C2SGetRankData = 5;</code>
     *
     * <pre>
     *获取排行榜数据
     * </pre>
     */
    C2SGetRankData(4, 5),
    /**
     * <code>S2CBrocastAll = 6;</code>
     *
     * <pre>
     *广播全服
     * </pre>
     */
    S2CBrocastAll(5, 6),
    /**
     * <code>S2CBrocastTargets = 7;</code>
     *
     * <pre>
     *广播对应目标
     * </pre>
     */
    S2CBrocastTargets(6, 7),
    /**
     * <code>S2SShutDown = 8;</code>
     *
     * <pre>
     *关机
     * </pre>
     */
    S2SShutDown(7, 8),
    /**
     * <code>C2SApplyJoinUnion = 9;</code>
     *
     * <pre>
     *申请加入公会
     * </pre>
     */
    C2SApplyJoinUnion(8, 9),
    /**
     * <code>S2CTip = 10;</code>
     *
     * <pre>
     *错误码提示
     * </pre>
     */
    S2CTip(9, 10),
    /**
     * <code>C2Slogout = 11;</code>
     *
     * <pre>
     * </pre>
     */
    C2Slogout(10, 11),
    /**
     * <code>C2SGetAllRole = 12;</code>
     *
     * <pre>
     *获取全部角色
     * </pre>
     */
    C2SGetAllRole(11, 12),
    /**
     * <code>S2SReload = 13;</code>
     *
     * <pre>
     *重载配置文件
     * </pre>
     */
    S2SReload(12, 13),
    /**
     * <code>S2SEnterFightServer = 14;</code>
     *
     * <pre>
     *进入战斗服,上报，连接服务器id
     * </pre>
     */
    S2SEnterFightServer(13, 14),
    /**
     * <code>C2SEnterUnion = 15;</code>
     *
     * <pre>
     *进入公会
     * </pre>
     */
    C2SEnterUnion(14, 15),
    /**
     * <code>C2SEXitUnion = 16;</code>
     *
     * <pre>
     *离开公会
     * </pre>
     */
    C2SEXitUnion(15, 16),
    /**
     * <code>S2SRmCurrentInstanceCode = 19;</code>
     */
    S2SRmCurrentInstanceCode(16, 19),
    /**
     * <code>S2COffline = 20;</code>
     *
     * <pre>
     *服务器呼叫客户端，注销当前账号
     * </pre>
     */
    S2COffline(17, 20),
    /**
     * <code>C2SCreateRoomCode = 21;</code>
     *
     * <pre>
     *创建房间
     * </pre>
     */
    C2SCreateRoomCode(18, 21),
    /**
     * <code>C2SExitRoomCode = 22;</code>
     *
     * <pre>
     *离开房间
     * </pre>
     */
    C2SExitRoomCode(19, 22),
    /**
     * <code>C2SJoinRoomCode = 23;</code>
     *
     * <pre>
     *加入房间
     * </pre>
     */
    C2SJoinRoomCode(20, 23),
    /**
     * <code>S2CJoinEoomCode = 24;</code>
     *
     * <pre>
     *加入房间
     * </pre>
     */
    S2CJoinEoomCode(21, 24),
    /**
     * <code>GateSend = 25;</code>
     *
     * <pre>
     *网关发送数据
     * </pre>
     */
    GateSend(22, 25),
    /**
     * <code>SendToGate = 26;</code>
     *
     * <pre>
     *发往网关数据
     * </pre>
     */
    SendToGate(23, 26),
    ;

    /**
     * <code>C2SLoginCode = 1;</code>
     */
    public static final int C2SLoginCode_VALUE = 1;
    /**
     * <code>C2SHeart = 2;</code>
     */
    public static final int C2SHeart_VALUE = 2;
    /**
     * <code>C2SEnterWorld = 3;</code>
     *
     * <pre>
     *进入游戏服
     * </pre>
     */
    public static final int C2SEnterWorld_VALUE = 3;
    /**
     * <code>C2SCreateRoleCode = 4;</code>
     *
     * <pre>
     *创建角色
     * </pre>
     */
    public static final int C2SCreateRoleCode_VALUE = 4;
    /**
     * <code>C2SGetRankData = 5;</code>
     *
     * <pre>
     *获取排行榜数据
     * </pre>
     */
    public static final int C2SGetRankData_VALUE = 5;
    /**
     * <code>S2CBrocastAll = 6;</code>
     *
     * <pre>
     *广播全服
     * </pre>
     */
    public static final int S2CBrocastAll_VALUE = 6;
    /**
     * <code>S2CBrocastTargets = 7;</code>
     *
     * <pre>
     *广播对应目标
     * </pre>
     */
    public static final int S2CBrocastTargets_VALUE = 7;
    /**
     * <code>S2SShutDown = 8;</code>
     *
     * <pre>
     *关机
     * </pre>
     */
    public static final int S2SShutDown_VALUE = 8;
    /**
     * <code>C2SApplyJoinUnion = 9;</code>
     *
     * <pre>
     *申请加入公会
     * </pre>
     */
    public static final int C2SApplyJoinUnion_VALUE = 9;
    /**
     * <code>S2CTip = 10;</code>
     *
     * <pre>
     *错误码提示
     * </pre>
     */
    public static final int S2CTip_VALUE = 10;
    /**
     * <code>C2Slogout = 11;</code>
     *
     * <pre>
     * </pre>
     */
    public static final int C2Slogout_VALUE = 11;
    /**
     * <code>C2SGetAllRole = 12;</code>
     *
     * <pre>
     *获取全部角色
     * </pre>
     */
    public static final int C2SGetAllRole_VALUE = 12;
    /**
     * <code>S2SReload = 13;</code>
     *
     * <pre>
     *重载配置文件
     * </pre>
     */
    public static final int S2SReload_VALUE = 13;
    /**
     * <code>S2SEnterFightServer = 14;</code>
     *
     * <pre>
     *进入战斗服,上报，连接服务器id
     * </pre>
     */
    public static final int S2SEnterFightServer_VALUE = 14;
    /**
     * <code>C2SEnterUnion = 15;</code>
     *
     * <pre>
     *进入公会
     * </pre>
     */
    public static final int C2SEnterUnion_VALUE = 15;
    /**
     * <code>C2SEXitUnion = 16;</code>
     *
     * <pre>
     *离开公会
     * </pre>
     */
    public static final int C2SEXitUnion_VALUE = 16;
    /**
     * <code>S2SRmCurrentInstanceCode = 19;</code>
     */
    public static final int S2SRmCurrentInstanceCode_VALUE = 19;
    /**
     * <code>S2COffline = 20;</code>
     *
     * <pre>
     *服务器呼叫客户端，注销当前账号
     * </pre>
     */
    public static final int S2COffline_VALUE = 20;
    /**
     * <code>C2SCreateRoomCode = 21;</code>
     *
     * <pre>
     *创建房间
     * </pre>
     */
    public static final int C2SCreateRoomCode_VALUE = 21;
    /**
     * <code>C2SExitRoomCode = 22;</code>
     *
     * <pre>
     *离开房间
     * </pre>
     */
    public static final int C2SExitRoomCode_VALUE = 22;
    /**
     * <code>C2SJoinRoomCode = 23;</code>
     *
     * <pre>
     *加入房间
     * </pre>
     */
    public static final int C2SJoinRoomCode_VALUE = 23;
    /**
     * <code>S2CJoinEoomCode = 24;</code>
     *
     * <pre>
     *加入房间
     * </pre>
     */
    public static final int S2CJoinEoomCode_VALUE = 24;
    /**
     * <code>GateSend = 25;</code>
     *
     * <pre>
     *网关发送数据
     * </pre>
     */
    public static final int GateSend_VALUE = 25;
    /**
     * <code>SendToGate = 26;</code>
     *
     * <pre>
     *发往网关数据
     * </pre>
     */
    public static final int SendToGate_VALUE = 26;


    public final int getNumber() { return value; }

    public static CommandCode valueOf(int value) {
      switch (value) {
        case 1: return C2SLoginCode;
        case 2: return C2SHeart;
        case 3: return C2SEnterWorld;
        case 4: return C2SCreateRoleCode;
        case 5: return C2SGetRankData;
        case 6: return S2CBrocastAll;
        case 7: return S2CBrocastTargets;
        case 8: return S2SShutDown;
        case 9: return C2SApplyJoinUnion;
        case 10: return S2CTip;
        case 11: return C2Slogout;
        case 12: return C2SGetAllRole;
        case 13: return S2SReload;
        case 14: return S2SEnterFightServer;
        case 15: return C2SEnterUnion;
        case 16: return C2SEXitUnion;
        case 19: return S2SRmCurrentInstanceCode;
        case 20: return S2COffline;
        case 21: return C2SCreateRoomCode;
        case 22: return C2SExitRoomCode;
        case 23: return C2SJoinRoomCode;
        case 24: return S2CJoinEoomCode;
        case 25: return GateSend;
        case 26: return SendToGate;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<CommandCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<CommandCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<CommandCode>() {
            public CommandCode findValueByNumber(int number) {
              return CommandCode.valueOf(number);
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
      return com.gproject.common.dto.proto.CommandCodeDTO.getDescriptor().getEnumTypes().get(0);
    }

    private static final CommandCode[] VALUES = values();

    public static CommandCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private CommandCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:gsp.CommandCode)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021CommandCode.proto\022\003gsp*\332\003\n\013CommandCode" +
      "\022\020\n\014C2SLoginCode\020\001\022\014\n\010C2SHeart\020\002\022\021\n\rC2SE" +
      "nterWorld\020\003\022\025\n\021C2SCreateRoleCode\020\004\022\022\n\016C2" +
      "SGetRankData\020\005\022\021\n\rS2CBrocastAll\020\006\022\025\n\021S2C" +
      "BrocastTargets\020\007\022\017\n\013S2SShutDown\020\010\022\025\n\021C2S" +
      "ApplyJoinUnion\020\t\022\n\n\006S2CTip\020\n\022\r\n\tC2Slogou" +
      "t\020\013\022\021\n\rC2SGetAllRole\020\014\022\r\n\tS2SReload\020\r\022\027\n" +
      "\023S2SEnterFightServer\020\016\022\021\n\rC2SEnterUnion\020" +
      "\017\022\020\n\014C2SEXitUnion\020\020\022\034\n\030S2SRmCurrentInsta" +
      "nceCode\020\023\022\016\n\nS2COffline\020\024\022\025\n\021C2SCreateRo",
      "omCode\020\025\022\023\n\017C2SExitRoomCode\020\026\022\023\n\017C2SJoin" +
      "RoomCode\020\027\022\023\n\017S2CJoinEoomCode\020\030\022\014\n\010GateS" +
      "end\020\031\022\016\n\nSendToGate\020\032B/\n\035com.gproject.co" +
      "mmon.dto.protoB\016CommandCodeDTO"
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
