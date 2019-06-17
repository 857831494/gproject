package com.gproject.net.netty;
//package com.gproject.utils.net;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//
//public class EncodeHandler extends MessageToByteEncoder<NetPack> {
//
//	
//
//	@Override
//	protected void encode(ChannelHandlerContext ctx, NetPack msg, ByteBuf out) throws Exception {
//		// TODO Auto-generated method stub
//		int dataLen=(msg.data==null?0:msg.data.length);
//		dataLen+=DecoderHandler.DATA_CMD;
//		byte[] lenArray=ByteUtil.intToByteArray(dataLen);
//		out.writeBytes(lenArray);
//		lenArray=ByteUtil.intToByteArray(msg.cmdCode);
//		
//		out.writeBytes(lenArray);
//		if (msg.data == null) {// ���û�а��壬���أ���������
//			return;
//		}
//		out.writeBytes(msg.data);// ���Ͱ����С
//	}
//
//	
//
//}