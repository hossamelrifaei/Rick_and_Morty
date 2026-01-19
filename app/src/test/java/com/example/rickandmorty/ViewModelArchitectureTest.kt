package com.example.rickandmorty

import com.example.mvi.common.BaseViewModel
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.core.importer.ClassFileImporter
import kotlin.test.Test

@AnalyzeClasses(packages = ["com.example.rickandmorty"])
class ViewModelArchitectureTest {



        @Test
        fun viewModelsMustExtendBase() {
            val rule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("ViewModel")
                .should().beAssignableTo(BaseViewModel::class.java)

            rule.check(ClassFileImporter().importPackages("com.example.rickandmorty"))
        }
    }
