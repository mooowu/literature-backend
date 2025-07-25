package lab.ujumeonji.literaturebackend.domain.novel.command

enum class StoryPhaseEnum(
    val koreanName: String,
    val description: String,
) {
    INTRODUCTION("발단", "스토리의 배경과 등장인물이 소개되고 사건의 발단이 제시되는 단계"),
    DEVELOPMENT("전개", "이야기가 본격적으로 전개되며 갈등이 심화되는 단계"),
    CRISIS("위기", "갈등이 최고조에 달하고 주인공이 중대한 시련을 맞이하는 단계"),
    CLIMAX("절정", "이야기의 핵심 갈등이 해결되거나 가장 극적인 순간을 맞이하는 단계"),
    RESOLUTION("결말", "모든 갈등이 해소되고 이야기가 마무리되는 단계"),
    ;

    fun toStoryPhase(): lab.ujumeonji.literaturebackend.domain.novel.StoryPhase {
        return lab.ujumeonji.literaturebackend.domain.novel.StoryPhase.valueOf(this.name)
    }

    companion object {
        fun fromStoryPhase(phase: lab.ujumeonji.literaturebackend.domain.novel.StoryPhase): StoryPhaseEnum {
            return valueOf(phase.name)
        }
    }
}
