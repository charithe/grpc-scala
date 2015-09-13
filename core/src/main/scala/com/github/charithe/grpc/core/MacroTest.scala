package com.github.charithe.grpc.core

import com.github.charithe.grpc.GrpcSamples.TestMessage1
import com.github.charithe.grpc.macrodef.ProtoToCaseClass

/**
 * Created by cell on 26/08/15.
 */
@ProtoToCaseClass[TestMessage1] object MacroTest

