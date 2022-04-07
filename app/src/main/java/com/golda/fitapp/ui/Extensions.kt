package com.golda.fitapp.ui

import android.annotation.SuppressLint
import com.broooapps.graphview.CurveGraphConfig
import com.broooapps.graphview.CurveGraphView
import com.broooapps.graphview.models.GraphData
import com.broooapps.graphview.models.PointMap
import com.golda.fitapp.R
import java.text.SimpleDateFormat

const val TIME_FORMAT = "hh:mm aaa"

@SuppressLint("SimpleDateFormat")
fun Long.toTimeFormatted(): String {
    return SimpleDateFormat(TIME_FORMAT).format(this)
}

fun CurveGraphView.setParams(y1: Int, y2: Int, y3: Int) {
    val line = PointMap()
    line.addPoint(0, 1)
    line.addPoint(0, 1)
    line.addPoint(1, y1)
    line.addPoint(2, 1)
    line.addPoint(3, y2)
    line.addPoint(4, 1)
    line.addPoint(5, y3)
    line.addPoint(6, 1)

    val lineParams = GraphData.builder(context)
        .setPointMap(line)
        .setGraphStroke(R.color.line_graph)
        .setPointColor(R.color.black)
        .setPointRadius(5)
        .animateLine(true)
        .setStraightLine(false)
        .build()

    val maxGraph = line.maxValue * 1.2
    this.setData(5, maxGraph.toInt(), lineParams)
}

fun CurveGraphView.init() {
    this.configure(
        CurveGraphConfig.Builder(context)
            .setAxisColor(R.color.transpapent)
            .setIntervalDisplayCount(7)
            .setGuidelineColor(R.color.transpapent)
            .setxAxisScaleTextColor(R.color.transpapent)
            .setyAxisScaleTextColor(R.color.transpapent)
            .setAnimationDuration(3000)
            .build()
    )


    val line = PointMap()
    line.addPoint(1, 1)
    line.addPoint(2, 1)
    line.addPoint(3, 1)
    line.addPoint(4, 1)
    line.addPoint(5, 1)
    line.addPoint(6, 1)

    val gd2 = GraphData.builder(context)
        .setPointMap(line)
        .setGraphStroke(R.color.line_graph)
        .build()

    this.setData(5, 1000, gd2)
}