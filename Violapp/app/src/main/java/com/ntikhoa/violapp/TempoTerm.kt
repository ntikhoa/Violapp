package com.ntikhoa.violapp

class TempoTerm(
    val term: String,
    val minTempo: Int,
    val maxTempo: Int
) {

    companion object {
        val TEMPO_TERMS = arrayOf<TempoTerm>(
            TempoTerm("Grave", 20, 39),
            TempoTerm("Lento", 40, 45),
            TempoTerm("Largo", 46, 50),
            TempoTerm("Larghetto", 51, 55),
            TempoTerm("Adagio", 56, 65),
            TempoTerm("Adagietto", 66, 72),
            TempoTerm("Andante", 73, 77),
            TempoTerm("Andantino", 78, 83),
            TempoTerm("Marcia moderato", 83, 85),
            TempoTerm("Moderato", 86, 97),
            TempoTerm("Allegretto", 98, 109),
            TempoTerm("Allegro", 110, 132),
            TempoTerm("Vivace", 133, 140),
            TempoTerm("Vivacissimo", 141, 150),
            TempoTerm("Allegrissimo", 151, 167),
            TempoTerm("Presto", 168, 177),
            TempoTerm("Prestissimo", 178, 300)
        )
    }

    fun getAVGtempo(): Int {
        return (maxTempo + minTempo) / 2
    }
}