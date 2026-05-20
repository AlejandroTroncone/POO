package org.example.model

class Trail(
    val id: Int,
    val name: String,
    val status: TrailStatus = TrailStatus.OPEN
) {
    private val courses = mutableListOf<Course>()

    init{
        require(id > 0) { "ID deve ser positivo"}
        require(name.isNotBlank()) {"Nome deve ser preenchido"}
    }
    fun addCourse(course: Course): Boolean {
        if (status != TrailStatus.OPEN) return false
        if (courses.any { it.id == course.id }) return false
        courses.add(course)
        return true
    }
    fun removeCourseById(id: Int): Boolean {
        if (status != TrailStatus.OPEN) return false
        return courses.removeIf { it.id == id }
    }
    fun totalWorkload(): Int = courses.sumOf { it.workloadHours }
    fun listCourses(): List<Course> = courses.toList()
}