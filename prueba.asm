    X: LD A,D
    Y: LD E,B
    LD A,L
    LD E,A
    CPD
ETI1: LD B,(IX+8)
    LD A,(BC)
    JP (HL)
    JP 1202
    EXX
ETI2: LD C,(HL)
    LD (HL),E
ETI3: LD B,A
    PUSH IX
    RET
ETI4: NOP
    HALT
    END