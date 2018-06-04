    X: LD A,D
    Y: LD E,B
    LD A,C
ETI1: LD A,(BC)
    LD A,(DE)
ETI2: LD C,B
    LD B,A
ETI3: LD E,C
    HALT
    END