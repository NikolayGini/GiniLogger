package com.example.gini_logger.data.default_implementation

import com.example.gini_logger.domain.LogBuilderProvider

class DefaultLogBuilderProvider : LogBuilderProvider<DefaultLogBuilder> {

    override fun provide(): DefaultLogBuilder = DefaultLogBuilder()
}