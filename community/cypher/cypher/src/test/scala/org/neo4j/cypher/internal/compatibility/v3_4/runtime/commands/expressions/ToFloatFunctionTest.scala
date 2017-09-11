/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compatibility.v3_4.runtime.commands.expressions

import org.neo4j.cypher.internal.compatibility.v3_4.runtime.ExecutionContext
import org.neo4j.cypher.internal.compatibility.v3_4.runtime.pipes.QueryStateHelper
import org.neo4j.cypher.internal.frontend.v3_4.ParameterWrongTypeException
import org.neo4j.cypher.internal.apa.v3_4.test_helpers.CypherFunSuite
import org.neo4j.values.storable.Values.{NO_VALUE, doubleValue}

class ToFloatFunctionTest extends CypherFunSuite {

  test("should return null if argument is null") {
    assert(toFloat(null) === NO_VALUE)
  }

  test("should convert a string to a float") {
    toFloat("10.599") should be(doubleValue(10.599))
  }

  test("should convert an integer string to a float") {
    toFloat("21") should be(doubleValue(21.0))
  }

  test("should convert an integer to a float") {
    toFloat(23) should be(doubleValue(23.0))
  }

  test("should return null if the argument is a partially numeric string") {
    assert(toFloat("20foobar2") === NO_VALUE)
  }

  test("should convert a string with leading zeros to a float") {
    toFloat("000123121.5") should be(doubleValue(123121.5))
  }

  test("should convert a string with leading minus to a negative float") {
    toFloat("-12.66") should be(doubleValue(-12.66))
  }

  test("should convert a string with leading minus and zeros to a negative float") {
    toFloat("-00012.91") should be(doubleValue(-12.91))
  }

  test("should throw an exception if the argument is an object which cannot be converted to a float") {
    val caughtException = evaluating { toFloat(true) } should produce[ParameterWrongTypeException]
    caughtException.getMessage should startWith("Expected a String or Number, got: ")
  }

  test("given a float should give the same value back") {
    toFloat(50.5) should be(doubleValue(50.5))
  }

  private def toFloat(orig: Any) = {
    ToFloatFunction(Literal(orig))(ExecutionContext.empty, QueryStateHelper.empty)
  }
}
