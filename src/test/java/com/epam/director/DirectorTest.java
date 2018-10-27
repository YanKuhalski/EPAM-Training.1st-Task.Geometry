package com.epam.director;

import com.emap.calculator.Calculator;
import com.emap.creation.IDGenerator;
import com.emap.creation.PointCreator;
import com.emap.creation.QuadrilateralCreator;
import com.emap.director.Director;
import com.emap.entities.Point;
import com.emap.entities.Quadrilateral;
import com.emap.parse.StringParser;
import com.emap.parse.impl.StringParserImpl;
import com.emap.reader.impl.DataReader;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DirectorTest {
    private final static DataReader dataReader = Mockito.mock(DataReader.class);
    private final static StringParser stringParser = Mockito.mock(StringParserImpl.class);
    private final static PointCreator pointCreator = Mockito.mock(PointCreator.class);
    private final static QuadrilateralCreator quadrilateralCreator = Mockito.mock(QuadrilateralCreator.class);
    private final static Calculator calculator = Mockito.mock(Calculator.class);
    private final static Director director = new Director(dataReader, stringParser, pointCreator, quadrilateralCreator, calculator);

    private  static
    List<Point> POINTS = Arrays.asList(
            new Point(0, 1),
            new Point(1, 1),
            new Point(1, 0),
            new Point(0, 0));


    @Test
    public void shouldReturnQuadrilateralListWhenPathIsCorrect() {
        Mockito.when(dataReader.readStrings(Mockito.any(String.class)))
                .thenReturn(Arrays.asList("dfsf"));
        Mockito.when(stringParser.parse(Mockito.any(String.class)))
                .thenReturn(new double[]{324, 32, 12});

        Mockito.when(pointCreator.creatPoints(Mockito.any(double[].class)))
                .thenReturn(POINTS);

        Mockito.when(quadrilateralCreator.create(Mockito.any(ArrayList.class)))
                .thenReturn(new Quadrilateral(POINTS, IDGenerator.generateQuadrilateralID()));

        List<Point> pointList = Arrays.asList(new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(0, 0));

        List<Quadrilateral> quadrilateralList = new ArrayList<>();
        quadrilateralList.add(new Quadrilateral(pointList, IDGenerator.generateQuadrilateralID()));


        List<Quadrilateral> quadrilaterals = director.createQuadrilateralList("dfs");

        Assert.assertEquals(quadrilateralList, quadrilaterals);
    }
}
