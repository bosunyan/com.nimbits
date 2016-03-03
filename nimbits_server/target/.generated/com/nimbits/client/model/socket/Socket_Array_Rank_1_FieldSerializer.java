package com.nimbits.client.model.socket;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Socket_Array_Rank_1_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.nimbits.client.model.socket.Socket[] instance) throws SerializationException {
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.deserialize(streamReader, instance);
  }
  
  public static com.nimbits.client.model.socket.Socket[] instantiate(SerializationStreamReader streamReader) throws SerializationException {
    int size = streamReader.readInt();
    return new com.nimbits.client.model.socket.Socket[size];
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, com.nimbits.client.model.socket.Socket[] instance) throws SerializationException {
    com.google.gwt.user.client.rpc.core.java.lang.Object_Array_CustomFieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.nimbits.client.model.socket.Socket_Array_Rank_1_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.nimbits.client.model.socket.Socket_Array_Rank_1_FieldSerializer.deserialize(reader, (com.nimbits.client.model.socket.Socket[])object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.nimbits.client.model.socket.Socket_Array_Rank_1_FieldSerializer.serialize(writer, (com.nimbits.client.model.socket.Socket[])object);
  }
  
}
