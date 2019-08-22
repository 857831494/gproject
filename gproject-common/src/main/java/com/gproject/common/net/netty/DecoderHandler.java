package com.gproject.common.net.netty;
//package com.gproject.utils.net;
//
//import java.util.List;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//public class DecoderHandler extends ByteToMessageDecoder {
//
//	/**
//	 * 最小包大小
//	 */
//	private final static int MIN_PACK_LEN=8;
//	
//	/**
//	 * 包长 大小
//	 */
//	final static int PACK_LEN=4;
//	
//	/**
//	 * 协议号 大小
//	 */
//	public final static int DATA_CMD=4;
//	
//	@Override
//	protected void decode(ChannelHandlerContext channelhandlercontext, 
//			ByteBuf bytebuf, List<Object> list)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//		if (bytebuf.readableBytes() < MIN_PACK_LEN) {// ǰ��4���ֽڱ�ʾ�����С��int�����ֵ�������
//			// ����int�����ֵ����Ϊ�쳣
//			return;
//		}
//		bytebuf.markReaderIndex();
//		
//		byte[] temp=new byte[PACK_LEN];
//		bytebuf.readBytes(temp);
//		int dataLen =ByteUtil.byteArrayToInt(temp);
//
//		
//		
//		if (bytebuf.readableBytes() < dataLen) {
//			// �������İ���С��û�дﵽ������Ҫ�ĳ��ȣ�������ζ�
//			bytebuf.resetReaderIndex();// ���ö�ȡ��λ��
//			return;
//		}
//		temp=new byte[DATA_CMD];
//		bytebuf.readBytes(temp);
//		int cmd=ByteUtil.byteArrayToInt(temp);
//		
//		
//		
//		byte[] body=null;
//		int logicLen=dataLen-DATA_CMD;
//		if (logicLen>0){
//			 body = new byte[logicLen];
//				bytebuf.readBytes(body);
//		}
//		
//		
//		list.add(new NetPack(cmd, body));// ������ȷ��ҵ�����ݣ���ҵ���򴫵�
//	}
//
//}