package hu.bme.mit.ga.metrics.tests;
// package eu.mondo.map.modelmetrics.tests;
//
// import static eu.mondo.map.tests.model.TestGraphConstants.type1;
// import static eu.mondo.map.tests.model.TestGraphConstants.type2;
// import static eu.mondo.map.tests.model.TestGraphConstants.type3;
// import static eu.mondo.map.tests.model.TestGraphConstants.type4;
// import static eu.mondo.map.tests.model.TestGraphConstants.network;
// import static eu.mondo.map.tests.model.TestGraphConstants.node1;
// import static eu.mondo.map.tests.model.TestGraphConstants.node2;
// import static eu.mondo.map.tests.model.TestGraphConstants.node3;
// import static eu.mondo.map.tests.model.TestGraphConstants.node4;
// import static eu.mondo.map.tests.model.TestGraphConstants.node5;
// import static eu.mondo.map.tests.model.TestGraphConstants.node6;
// import static eu.mondo.map.tests.model.TestGraphConstants.node7;
// import static eu.mondo.map.tests.model.TestGraphConstants.node8;
//
// import org.junit.Test;
//
// import eu.mondo.map.base.graph.Node;
// import eu.mondo.map.base.tests.ListDataTesterUtil;
// import eu.mondo.map.modelmetrics.impl.typed.NodeInterdependence;
//
// public class NodeInterdependenceTest extends ListDataTesterUtil<Double,
// NodeInterdependence<String>> {
//
// @Override
// public NodeInterdependence<String> initMetric() {
// return new NodeInterdependence<>();
// }
//
// @Override
// public void clear() {
// network.clear();
// }
//
// protected void calculate() {
// metric.calculate(network);
// }
//
// protected void calculate(int numberOfRandomPairs) {
// metric.calculate(network, numberOfRandomPairs);
// }
//
// protected void calculate(String source, String target) {
// Node<String> sourceNode = network.getNode(source);
// Node<String> targetNode = network.getNode(target);
//
// metric.calculate(network, sourceNode, targetNode);
// }
//
// @Test
// public void nullPath() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
//
// calculate(node3, node2);
// checkSize(0);
// }
//
// @Test
// public void nullPath2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node3, node4);
// network.addEdge(type1, node2, node4);
//
// calculate(node1, node3);
// checkSize(0);
//
// }
//
// @Test(expected = IllegalArgumentException.class)
// public void nullSource() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
//
// calculate(node4, node2);
// }
//
// @Test(expected = IllegalArgumentException.class)
// public void nullTarget() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
//
// calculate(node1, node4);
// }
//
// @Test
// public void absentSource() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addNode(node4);
//
// calculate(node4, node3);
// checkSize(0);
//
// }
//
// @Test
// public void absentTarget() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addNode(node4);
//
// calculate(node1, node4);
// checkSize(0);
// }
//
// @Test
// public void singlePath1() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
//
// calculate(node1, node3);
// checkSize(1);
// checkAppearance(1, 0.0);
//
// }
//
// @Test
// public void singlePath2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
//
// calculate(node2, node3);
// checkSize(1);
// checkAppearance(1, 0.0);
// }
//
// @Test
// public void singlePath3() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addEdge(type1, node1, node3);
//
// calculate(node1, node3);
// checkSize(1);
// checkAppearance(1, 0.0);
// }
//
// @Test
// public void singlePath4() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node3, node2);
// network.addEdge(type1, node3, node1);
// network.addEdge(type1, node2, node1);
// network.addEdge(type1, node2, node3);
//
// calculate(node1, node3);
// checkSize(1);
// checkAppearance(1, 0.0);
// }
//
// @Test
// public void singlePath5() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type1, node1, node4);
// network.addEdge(type1, node2, node5);
// network.addEdge(type1, node2, node8);
// network.addEdge(type1, node2, node6);
// network.addEdge(type1, node3, node6);
// network.addEdge(type1, node4, node7);
// network.addEdge(type1, node4, node6);
// network.addEdge(type1, node5, node8);
// network.addEdge(type1, node6, node8);
// network.addEdge(type1, node7, node8);
//
// calculate(node1, node8);
// checkSize(1);
// checkAppearance(1, 0.0);
// }
//
// @Test
// public void singlePathMultitypeal() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node1);
// network.addEdge(type2, node2, node3);
//
// calculate(node1, node3);
// calculate(node1, node2);
// calculate(node3, node1);
// checkSize(2);
// checkAppearance(1, 1.0);
// checkAppearance(1, 0.0);
// }
//
// @Test
// public void singlePathMultitypeal2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addEdge(type2, node2, node3);
//
// calculate(node1, node3);
// calculate(node1, node2);
// calculate(node2, node3);
// calculate(node2, node1);
// calculate(node3, node2);
// checkSize(3);
// checkAppearance(1, 0.5);
// checkAppearance(2, 0.0);
// }
//
// @Test
// public void multiplePath2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node4);
// network.addEdge(type1, node2, node3);
// network.addEdge(type2, node2, node3);
// network.addEdge(type1, node4, node3);
//
// calculate(node1, node2);
// calculate(node1, node3);
// calculate(node2, node3);
// calculate(node2, node1);
// calculate(node3, node2);
// calculate(node4, node3);
// checkSize(4);
// double expected = 1 / 3.0;
// checkAppearance(1, expected);
// checkAppearance(3, 0.0);
// }
//
// @Test
// public void multiplePath3() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node3, node5);
// network.addEdge(type1, node4, node6);
// network.addEdge(type1, node5, node6);
//
// calculate(node1, node6);
// calculate(node1, node3);
// calculate(node1, node4);
//
// checkSize(3);
// checkAppearance(3, 0.0);
//
// }
//
// @Test
// public void multiplePath3Extended() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node3, node5);
// network.addEdge(type2, node3, node5);
// network.addEdge(type1, node4, node6);
// network.addEdge(type1, node5, node6);
//
// calculate(node1, node6);
// calculate(node1, node3);
// calculate(node1, node4);
// calculate(node1, node5);
//
// checkSize(4);
// double expected = 1 / 3.0;
// checkAppearance(2, 0.0);
// checkAppearance(1, expected);
// checkAppearance(1, 0.5);
//
// }
//
// @Test
// public void multiplePath3Extended2() {
// network.addEdge(type2, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node3, node5);
// network.addEdge(type2, node3, node5);
// network.addEdge(type3, node3, node5);
// network.addEdge(type1, node4, node6);
// network.addEdge(type1, node5, node6);
// network.addEdge(type4, node5, node6);
//
// calculate(node1, node2);
// calculate(node1, node3);
// calculate(node1, node4);
// calculate(node1, node5);
// calculate(node1, node6);
//
// checkSize(5);
// double expected = 2 / 3.0;
// checkAppearance(2, 0.0);
// checkAppearance(1, expected);
// checkAppearance(1, 1.0);
// expected = 6 / 7.0;
// checkAppearance(1, expected);
//
// }
//
// @Test
// public void multiplePath4() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type2, node1, node3);
// network.addEdge(type3, node1, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node3, node5);
// network.addEdge(type1, node4, node6);
// network.addEdge(type3, node4, node6);
// network.addEdge(type1, node5, node6);
// network.addEdge(type2, node5, node6);
// network.addEdge(type3, node5, node6);
// network.addEdge(type2, node6, node7);
//
// calculate(node1, node2);
// calculate(node1, node3);
// calculate(node1, node4);
// calculate(node1, node5);
// calculate(node1, node6);
// calculate(node5, node6);
// calculate(node4, node7);
//
// checkSize(7);
// double expected = 2 / 3.0;
// checkAppearance(4, 0.0);
// checkAppearance(1, expected);
// checkAppearance(1, 1.0);
// expected = 9 / 11.0;
// checkAppearance(1, expected);
//
// }
//
// @Test
// public void multiplePath5() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node2, node5);
// network.addEdge(type1, node3, node5);
// network.addEdge(type1, node4, node6);
// network.addEdge(type1, node4, node3);
// network.addEdge(type1, node5, node6);
//
// calculate(node1, node2);
// calculate(node1, node3);
// calculate(node1, node4);
// calculate(node1, node5);
// calculate(node1, node6);
// calculate(node5, node6);
//
// checkSize(6);
// checkAppearance(6, 0.0);
//
// }
//
// @Test(expected = IllegalArgumentException.class)
// public void testRandomSampleException1() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// calculate(-2);
// }
//
// @Test(expected = IllegalArgumentException.class)
// public void testRandomSampleException2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// calculate(0);
// }
//
// @Test(expected = IllegalArgumentException.class)
// public void testRandomSampleException3() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
// calculate(7);
// }
//
// @Test
// public void testRandomSample1() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
//
// calculate(6);
// checkSize(2);
// checkAppearance(2, 0.0);
// }
//
// @Test
// public void testRandomSample2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node2, node5);
// network.addEdge(type1, node4, node5);
//
// calculate(20);
// checkSize(8);
// checkAppearance(8, 0.0);
// }
//
// @Test
// public void testCalculationforAll1() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node1, node3);
//
// calculate();
//
// checkSize(2);
// checkAppearance(2, 0.0);
// }
//
// @Test
// public void testCalculationforAll2() {
// network.addEdge(type1, node1, node2);
// network.addEdge(type1, node2, node3);
// network.addEdge(type1, node2, node4);
// network.addEdge(type1, node2, node5);
// network.addEdge(type1, node4, node5);
//
// calculate();
//
// checkSize(8);
// checkAppearance(8, 0.0);
// }
//
// }
