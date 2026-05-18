package model;

data class Course(
        val id: Int,
        val title: String,
        val workloadHours: Int,
        val level: CourseLevel,
        val status: CourseStatus

){

}
