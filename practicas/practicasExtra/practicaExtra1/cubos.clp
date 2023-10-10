(deftemplate on-top-of
(slot upper)
(slot lower)
)

(deftemplate goal
(slot move)
(slot on-top)
)

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
(goal (move D)(on-top A)) )

(defrule final
?f1 <- (goal (move ?move) (on-top ?topOf))
?f2 <- (on-top-of (upper ?upper&nothing)(lower ?topOf))
?f3 <- (on-top-of (upper ?upper&nothing)(lower ?move))
=>
(assert(on-top-of (upper ?move) (lower ?topOf)))
(retract ?f1 ?f2 ?f3)
)


