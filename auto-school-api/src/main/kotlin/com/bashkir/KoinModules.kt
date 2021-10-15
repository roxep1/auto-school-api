package com.bashkir

import com.bashkir.services.EmployeesService
import org.koin.dsl.module

val servicesModule = module {
    single { EmployeesService() }
}