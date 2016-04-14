package org.neo4j.cypher.internal.spi.v3_1.codegen

import java.util

import org.neo4j.collection.primitive.{PrimitiveLongIntMap, PrimitiveLongIterator}
import org.neo4j.cypher.internal.codegen.CompiledConversionUtils.CompositeKey
import org.neo4j.cypher.internal.codegen._
import org.neo4j.cypher.internal.compiler.v3_1.codegen.{QueryExecutionEvent, QueryExecutionTracer, ResultRowImpl}
import org.neo4j.cypher.internal.compiler.v3_1.planDescription.Id
import org.neo4j.cypher.internal.compiler.v3_1.spi.{InternalResultRow, InternalResultVisitor}
import org.neo4j.graphdb.Direction
import org.neo4j.helpers.collection.MapUtil
import org.neo4j.kernel.api.ReadOperations
import org.neo4j.kernel.api.index.IndexDescriptor
import org.neo4j.kernel.impl.api.store.RelationshipIterator
import org.neo4j.kernel.impl.api.{RelationshipDataExtractor, RelationshipVisitor}
import org.neo4j.kernel.impl.core.{NodeManager, NodeProxy, RelationshipProxy}

private object Methods {

  import GeneratedQueryStructure.{method, typeRef}

  val countingTablePut = method[PrimitiveLongIntMap, Int]("put", typeRef[Long], typeRef[Int])
  val countingTableCompositeKeyPut = method[util.HashMap[_, _], Int]("put", typeRef[CompositeKey], typeRef[Int])
  val countingTableGet = method[PrimitiveLongIntMap, Int]("get", typeRef[Long])
  val countingTableCompositeKeyGet = method[util.HashMap[_, _], Int]("get", typeRef[CompositeKey])
  val compositeKey = method[CompiledConversionUtils, CompositeKey]("compositeKey", typeRef[Array[Long]])
  val hasNextLong = method[PrimitiveLongIterator, Boolean]("hasNext")
  val hasNextRelationship = method[RelationshipIterator, Boolean]("hasNext")
  val createMap = method[MapUtil, util.Map[String, Object]]("map", typeRef[Array[Object]])
  val relationshipVisit = method[RelationshipIterator, Boolean]("relationshipVisit", typeRef[Long], typeRef[RelationshipVisitor[RuntimeException]])
  val relationship = method[RelationshipDataExtractor, Long]("relationship")
  val startNode = method[RelationshipDataExtractor, Long]("startNode")
  val endNode = method[RelationshipDataExtractor, Long]("endNode")
  val typeOf = method[RelationshipDataExtractor, Int]("type")
  val nodeGetAllRelationships = method[CompiledReadOperationsUtils, RelationshipIterator]("nodeGetRelationships", typeRef[ReadOperations], typeRef[Long], typeRef[Direction])
  val nodeGetRelationships = method[ReadOperations, RelationshipIterator]("nodeGetRelationships", typeRef[Long], typeRef[Direction], typeRef[Array[Int]])
  val allConnectingRelationships = method[CompiledExpandUtils, RelationshipIterator]("connectingRelationships", typeRef[ReadOperations], typeRef[Long], typeRef[Long], typeRef[Direction])
  val connectingRelationships = method[CompiledExpandUtils, RelationshipIterator]("connectingRelationships", typeRef[ReadOperations], typeRef[Long], typeRef[Long], typeRef[Direction], typeRef[Array[Int]])
  val mathAdd = method[CompiledMathHelper, Object]("add", typeRef[Object], typeRef[Object])
  val mathSub = method[CompiledMathHelper, Object]("subtract", typeRef[Object], typeRef[Object])
  val mathMul = method[CompiledMathHelper, Object]("multiply", typeRef[Object], typeRef[Object])
  val mathDiv = method[CompiledMathHelper, Object]("divide", typeRef[Object], typeRef[Object])
  val mathMod = method[CompiledMathHelper, Object]("modulo", typeRef[Object], typeRef[Object])
  val mathCastToInt = method[CompiledMathHelper, Int]("transformToInt", typeRef[Object])
  val mapGet = method[util.Map[String, Object], Object]("get", typeRef[Object])
  val mapContains = method[util.Map[String, Object], Boolean]("containsKey", typeRef[Object])
  val labelGetForName = method[ReadOperations, Int]("labelGetForName", typeRef[String])
  val propertyKeyGetForName = method[ReadOperations, Int]("propertyKeyGetForName", typeRef[String])
  val coerceToPredicate = method[CompiledConversionUtils, Boolean]("coerceToPredicate", typeRef[Object])
  val toCollection = method[CompiledConversionUtils, java.util.Collection[Object]]("toCollection", typeRef[Object])
  val ternaryEquals = method[CompiledConversionUtils, java.lang.Boolean]("equals", typeRef[Object], typeRef[Object])
  val equals = method[Object, Boolean]("equals", typeRef[Object])
  val or = method[CompiledConversionUtils, java.lang.Boolean]("or", typeRef[Object], typeRef[Object])
  val not = method[CompiledConversionUtils, java.lang.Boolean]("not", typeRef[Object])
  val loadParameter = method[CompiledConversionUtils, java.lang.Object]("loadParameter", typeRef[Object])
  val relationshipTypeGetForName = method[ReadOperations, Int]("relationshipTypeGetForName", typeRef[String])
  val relationshipTypeGetName = method[ReadOperations, String]("relationshipTypeGetName", typeRef[Int])
  val nodesGetAll = method[ReadOperations, PrimitiveLongIterator]("nodesGetAll")
  val nodeGetProperty = method[ReadOperations, Object]("nodeGetProperty")
  val nodesGetFromIndexLookup = method[ReadOperations, PrimitiveLongIterator]("nodesGetFromIndexSeek", typeRef[IndexDescriptor], typeRef[Object])
  val nodeGetUniqueFromIndexLookup = method[ReadOperations, Long]("nodeGetFromUniqueIndexSeek", typeRef[IndexDescriptor], typeRef[Object])
  val relationshipGetProperty = method[ReadOperations, Object]("relationshipGetProperty")
  val nodesGetForLabel = method[ReadOperations, PrimitiveLongIterator]("nodesGetForLabel", typeRef[Int])
  val nodeHasLabel = method[ReadOperations, Boolean]("nodeHasLabel", typeRef[Long], typeRef[Int])
  val nextLong = method[PrimitiveLongIterator, Long]("next")
  val nextRelationship = method[RelationshipIterator, Long]("next")
  val newNodeProxyById = method[NodeManager, NodeProxy]("newNodeProxyById", typeRef[Long])
  val nodeId = method[NodeIdWrapper, Long]("id")
  val relId = method[RelationshipIdWrapper, Long]("id")
  val newRelationshipProxyById = method[NodeManager, RelationshipProxy]("newRelationshipProxyById")
  val set = method[ResultRowImpl, Unit]("set", typeRef[String], typeRef[Object])
  val visit = method[InternalResultVisitor[_], Boolean]("visit", typeRef[InternalResultRow])
  val executeOperator = method[QueryExecutionTracer, QueryExecutionEvent]("executeOperator", typeRef[Id])
  val dbHit = method[QueryExecutionEvent, Unit]("dbHit")
  val row = method[QueryExecutionEvent, Unit]("row")
  val boxBoolean = method[java.lang.Boolean, java.lang.Boolean]("valueOf", typeRef[Boolean])
  val boxLong = method[java.lang.Long, java.lang.Long]("valueOf", typeRef[Long])
  val boxDouble = method[java.lang.Double, java.lang.Double]("valueOf", typeRef[Double])
}
