/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package ac.upc.edu;

import org.jdmp.core.algorithm.classification.KNNClassifier;
import org.jdmp.core.algorithm.classification.bayes.NaiveBayesClassifier;
import org.jdmp.core.algorithm.regression.LinearRegression;
import org.jdmp.core.dataset.DataSet;
import org.jdmp.core.dataset.ListDataSet;
import org.jdmp.mallet.classifier.MalletClassifier;
import org.jdmp.weka.clusterer.WekaClusterer;
import org.openjdk.jmh.annotations.*;
import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation;

import java.util.concurrent.TimeUnit;

import static org.jdmp.mallet.classifier.MalletClassifier.MalletClassifiers.DecisionTree;

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class MyBenchmark {

    // EXAMPLE KNN USAGE
    public void knnExample() {
        // Load example data set
        ListDataSet dataSet = DataSet.Factory.ANIMALS();

        // Create the classifier - for the sake of this example we will use
        KNNClassifier classifier = new KNNClassifier(5);

        // Train the classifier using all data
        classifier.trainAll(dataSet);

        // Use the classifier to make predictions
        classifier.predictAll(dataSet);

        // Get the results - no needed for benchmark purposes
        // double accuracy = dataSet.getAccuracy();

        // System.out.println("accuracy: " + accuracy);
    }

    // EXAMPLE DECISION TREE USAGE
    public void decisionTreeExample() {
        // Load example data set
        ListDataSet dataSet = DataSet.Factory.ANIMALS();

        // Create the classifier - for the sake of this example we will use
        MalletClassifier classifier = new MalletClassifier(DecisionTree);

        // Train the classifier using all data
        classifier.trainAll(dataSet);

        // Use the classifier to make predictions
        classifier.predictAll(dataSet);

        // Get the results - no needed for benchmark purposes
        // double accuracy = dataSet.getAccuracy();

        // System.out.println("accuracy: " + accuracy);
    }

    // EXAMPLE LINEAR REGRESSION USAGE
    public void linearRegressionExample() {
        // Load example data set
        ListDataSet dataSet = DataSet.Factory.ANIMALS();

        // Create the classifier - for the sake of this example we will use
        LinearRegression classifier = new LinearRegression();

        // Train the classifier using all data
        classifier.trainAll(dataSet);

        // Use the classifier to make predictions
        classifier.predictAll(dataSet);

        // Get the results - no needed for benchmark purposes
        // double accuracy = dataSet.getAccuracy();

        // System.out.println("accuracy: " + accuracy);
    }

    // EXAMPLE KMEANS USAGE
    public void testClusteringKMeans() throws Exception {
        ListDataSet iris = ListDataSet.Factory.IRIS();
        WekaClusterer wc = new WekaClusterer(WekaClusterer.WekaClustererType.SimpleKMeans, false);
        wc.setNumberOfClusters(3);
        wc.train(iris);
        wc.predict(iris);

        Matrix result = iris.getPredictedMatrix().sum(Calculation.Ret.NEW, Matrix.ROW, true);
    }

    @Benchmark
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void irisDataSetCreation() {
        DataSet.Factory.IRIS();
    }

    @Benchmark
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void animalsDataSetCreation() {
        DataSet.Factory.ANIMALS();
    }
}
