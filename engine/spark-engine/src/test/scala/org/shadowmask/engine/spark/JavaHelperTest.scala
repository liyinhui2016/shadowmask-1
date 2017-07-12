/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.shadowmask.engine.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test
import org.shadowmask.engine.spark.partitioner.RolbinPartitioner


class JavaHelperTest {
  @Test
  def testRepartition(): Unit ={
    val conf = new SparkConf().setAppName("shadowmask").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(1 to 100)

    JavaHelper.rddRepartition(sc,rdd,2,SparkUtil.executorNames(sc)(0))(new RolbinPartitioner(2))
  }
}