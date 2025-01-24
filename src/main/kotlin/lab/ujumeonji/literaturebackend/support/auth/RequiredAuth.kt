package lab.ujumeonji.literaturebackend.support.auth

import io.swagger.v3.oas.annotations.Hidden

@Hidden
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiredAuth
