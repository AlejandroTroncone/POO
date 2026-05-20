package org.example.model

class Coursecatalog {
    val courses = mutableListOf<Course>()

    fun add(course: Course): Boolean {
        if (courses.any { it.id == course.id }) return false
        courses.add(course)
        return true
    }
    fun findById(id: Int): Course? {
        return courses.find { it.id == id }
    }
    fun listOrderedByTitle(): List<Course> {
        return courses.sortedBy { it.title }
    }
    fun filterByLevel(level: CourseLevel): List<Course> {
        return courses.filter { it.level == level }
    }
    fun totalWorkLoad(): Int {
        return courses.sumOf { it.workloadHours }
    }
}