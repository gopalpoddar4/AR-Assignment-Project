package com.gopalpoddar4.arappproject

object DrillDataProvider {
    val data = listOf(
        RCVModel(
            id = 1,
            image = R.drawable.drill_construction, // Replace with your image
            discription = "Construction Safety Drill ensures proper PPE usage, fall protection and site inspection procedures.",
            tip = "Always double-check your harness and helmet before entering high-risk zones.",
            drill = "Construction Safety Drill"
        ),
        RCVModel(
            id = 2,
            image = R.drawable.drill_fire, // Replace with your image
            discription = "Fire Safety Drill covers evacuation routes, fire extinguisher usage and emergency response protocol.",
            tip = "Know your nearest exit and how to use a fire extinguisher (PASS: Pull, Aim, Squeeze, Sweep).",
            drill = "Fire Safety Drill"
        ),
        RCVModel(
            id = 3,
            image = R.drawable.drill_electrical, // Replace with your image
            discription = "Electrical Hazard Drill teaches how to identify faulty wiring, handle live wires, and shut down circuits safely.",
            tip = "Never touch a live wire even with gloves unless circuit is shut off.",
            drill = "Electrical Hazard Drill"
        ),
        RCVModel(
            id = 4,
            image = R.drawable.drill_first_aid, // Replace with your image
            discription = "First Aid Drill includes CPR training, bleeding control, and handling fractures or burns.",
            tip = "Check for responsiveness before starting CPR. Use 30:2 compressions.",
            drill = "First Aid & CPR Drill"
        ),
        RCVModel(
            id = 5,
            image = R.drawable.drill_chemical, // Replace with your image
            discription = "Chemical Spill Drill guides on PPE usage, containment steps, and emergency shower procedures.",
            tip = "Always wear gloves, mask, and goggles when handling chemical spills.",
            drill = "Chemical Spill Response Drill"
        )
    )


    fun getDrillById(id: Int): RCVModel?{
        return data.find { it.id == id }
    }

    fun getDrillData(): List<RCVModel>{
        return data
    }
}