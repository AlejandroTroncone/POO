package org.example.model

data class Course(
    val id: Int,
    val title: String,
    val workloadHours: Int,
    val level: CourseLevel
){
    init {
        require (id > 0) {"ID must be positive"}
        require (title.isNotEmpty()) {"Tittle must not be empty"}
        require (workloadHours > 0) {"Workload must be positive"}
    }
    fun summary(): String {
        return "[$id] $title, ${workloadHours}h, ${level}"
    }

}