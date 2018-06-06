    X: LD A,D
    Y: LD E,B
    LD A,C
ETI1: LD B,(IX+8)
    LD A,(BC)
ETI2: LD C,(HL)
    LD (HL),E
ETI3: LD (IY+9),D
    LD B,A
    HALT
    END