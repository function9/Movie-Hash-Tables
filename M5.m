%Voravee Upatising M4
%part a
Voc = 3 / ((1/5) + (1/20) - (3/8))
Ia = [((Voc-10)/5) (Voc/20) (9) (-1)]
KCLcheck = sum(Ia)
%partb
Vb = 3 / ((1/5) + (1/20) - (3/8) +(1/2))
Ib = [((Vb-10)/5) (Vb/20) (-3) (-1) (Vb/2)]
KCLcheck2 = sum(Ib)
Isc = Ib(5)
%part c
Rth = Voc/Isc