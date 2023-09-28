(deffacts initial-state
(block A)
(block B)
(block C)
(block D)
(block E)
(block F)
(on-top-of (upper nothing)(lower A))
(on-top-of (upper A)(lower B))
(on-top-of (upper B)(lower C))
(on-top-of (upper C)(lower floor))
(on-top-of (upper nothing)(lower D))
(on-top-of (upper D)(lower E))
(on-top-of (upper E)(lower F))
(on-top-of (upper F)(lower floor))
(goal (move F)(on-top C)) )

(deftemplate on-top-of
(slot upper)
(slot lower)
)

(deftemplate goal
(slot move)
(slot on-top)
)

(defrule on-top-of
(on-top-of ?upper ?lower)
=>
(assert (on-top-of ?upper ?lower))
)

(defrule final
(goal (move ?move) (on-top ?topOf))
(on-top-of (upper ?upper&nothing)(lower ?lower))
=>
(on-top-of (upper ?move) (lower ?block))
)

(defrule upper
?f1 <- (upper ?block)
=>
(retract ?f1)
(assert(upper ?block))
)

(defrule move 
(move ?move)
(on-top-of ?block)
(upper nothing)
(lower ?block)
=>
(retract ?f1)
(assert (on-top-of (upper ?move)(lower ?block)))
)


