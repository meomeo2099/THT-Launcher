
docs/reference-analysis/artifacts/deep/native/armeabi-v7a/libdhguard.so:     file format elf32-littlearm


Disassembly of section .text:

000006d0 <Java_org_reference_panel_Guard_resolve@@Base-0x54>:
 6d0:	e59f0004 	ldr	r0, [pc, #4]	@ 6dc <Java_org_reference_panel_Guard_resolve@@Base-0x48>
 6d4:	e08f0000 	add	r0, pc, r0
 6d8:	ea000194 	b	d30 <Java_org_reference_panel_Guard_probe@@Base+0x5e8>
 6dc:	000016a4 	andeq	r1, r0, r4, lsr #13
 6e0:	e12fff1e 	bx	lr
 6e4:	eafffffd 	b	6e0 <Java_org_reference_panel_Guard_resolve@@Base-0x44>
 6e8:	e3500000 	cmp	r0, #0
 6ec:	012fff1e 	bxeq	lr
 6f0:	e12fff10 	bx	r0
 6f4:	e1a01000 	mov	r1, r0
 6f8:	e59f000c 	ldr	r0, [pc, #12]	@ 70c <Java_org_reference_panel_Guard_resolve@@Base-0x18>
 6fc:	e59f200c 	ldr	r2, [pc, #12]	@ 710 <Java_org_reference_panel_Guard_resolve@@Base-0x14>
 700:	e08f0000 	add	r0, pc, r0
 704:	e08f2002 	add	r2, pc, r2
 708:	ea00018c 	b	d40 <Java_org_reference_panel_Guard_probe@@Base+0x5f8>
 70c:	ffffffe0 			@ <UNDEFINED> instruction: 0xffffffe0
 710:	00001674 	andeq	r1, r0, r4, ror r6
 714:	e59f3004 	ldr	r3, [pc, #4]	@ 720 <Java_org_reference_panel_Guard_resolve@@Base-0x4>
 718:	e08f3003 	add	r3, pc, r3
 71c:	ea00018b 	b	d50 <Java_org_reference_panel_Guard_probe@@Base+0x608>
 720:	00001660 	andeq	r1, r0, r0, ror #12

00000724 <Java_org_reference_panel_Guard_resolve@@Base>:
 724:	2a13      	cmp	r2, #19
 726:	bf84      	itt	hi
 728:	2000      	movhi	r0, #0
 72a:	4770      	bxhi	lr
 72c:	4804      	ldr	r0, [pc, #16]	@ (740 <Java_org_reference_panel_Guard_resolve@@Base+0x1c>)
 72e:	f649 617b 	movw	r1, #40571	@ 0x9e7b
 732:	f2ca 51c3 	movt	r1, #42435	@ 0xa5c3
 736:	4478      	add	r0, pc
 738:	f850 0022 	ldr.w	r0, [r0, r2, lsl #2]
 73c:	4048      	eors	r0, r1
 73e:	4770      	bx	lr
 740:	fd46 ffff 	stc2l	15, cr15, [r6, #-1020]	@ 0xfffffc04
 744:	d4d4      	bmi.n	6f0 <Java_org_reference_panel_Guard_resolve@@Base-0x34>
 746:	d4d4      	bmi.n	6f2 <Java_org_reference_panel_Guard_resolve@@Base-0x32>

00000748 <Java_org_reference_panel_Guard_probe@@Base>:
 748:	b5f0      	push	{r4, r5, r6, r7, lr}
 74a:	af03      	add	r7, sp, #12
 74c:	e92d 0f00 	stmdb	sp!, {r8, r9, sl, fp}
 750:	b081      	sub	sp, #4
 752:	ed2d eb04 	vpush	{d14-d15}
 756:	ed2d 8b0a 	vpush	{d8-d12}
 75a:	b0b6      	sub	sp, #216	@ 0xd8
 75c:	4683      	mov	fp, r0
 75e:	48d0      	ldr	r0, [pc, #832]	@ (aa0 <Java_org_reference_panel_Guard_probe@@Base+0x358>)
 760:	4615      	mov	r5, r2
 762:	4478      	add	r0, pc
 764:	4629      	mov	r1, r5
 766:	6800      	ldr	r0, [r0, #0]
 768:	6800      	ldr	r0, [r0, #0]
 76a:	9035      	str	r0, [sp, #212]	@ 0xd4
 76c:	f8db 0000 	ldr.w	r0, [fp]
 770:	6fc2      	ldr	r2, [r0, #124]	@ 0x7c
 772:	4658      	mov	r0, fp
 774:	4790      	blx	r2
 776:	4606      	mov	r6, r0
 778:	f8db 0000 	ldr.w	r0, [fp]
 77c:	4ac9      	ldr	r2, [pc, #804]	@ (aa4 <Java_org_reference_panel_Guard_probe@@Base+0x35c>)
 77e:	4631      	mov	r1, r6
 780:	4bc9      	ldr	r3, [pc, #804]	@ (aa8 <Java_org_reference_panel_Guard_probe@@Base+0x360>)
 782:	f8d0 4084 	ldr.w	r4, [r0, #132]	@ 0x84
 786:	447a      	add	r2, pc
 788:	447b      	add	r3, pc
 78a:	4658      	mov	r0, fp
 78c:	47a0      	blx	r4
 78e:	4680      	mov	r8, r0
 790:	f8db 0000 	ldr.w	r0, [fp]
 794:	4ac5      	ldr	r2, [pc, #788]	@ (aac <Java_org_reference_panel_Guard_probe@@Base+0x364>)
 796:	4631      	mov	r1, r6
 798:	4bc5      	ldr	r3, [pc, #788]	@ (ab0 <Java_org_reference_panel_Guard_probe@@Base+0x368>)
 79a:	f8d0 4084 	ldr.w	r4, [r0, #132]	@ 0x84
 79e:	447a      	add	r2, pc
 7a0:	447b      	add	r3, pc
 7a2:	4658      	mov	r0, fp
 7a4:	47a0      	blx	r4
 7a6:	4606      	mov	r6, r0
 7a8:	4658      	mov	r0, fp
 7aa:	4629      	mov	r1, r5
 7ac:	4642      	mov	r2, r8
 7ae:	f000 f991 	bl	ad4 <Java_org_reference_panel_Guard_probe@@Base+0x38c>
 7b2:	4680      	mov	r8, r0
 7b4:	4658      	mov	r0, fp
 7b6:	4629      	mov	r1, r5
 7b8:	4632      	mov	r2, r6
 7ba:	f000 f98b 	bl	ad4 <Java_org_reference_panel_Guard_probe@@Base+0x38c>
 7be:	4605      	mov	r5, r0
 7c0:	f8db 0000 	ldr.w	r0, [fp]
 7c4:	4641      	mov	r1, r8
 7c6:	6fc2      	ldr	r2, [r0, #124]	@ 0x7c
 7c8:	4658      	mov	r0, fp
 7ca:	4790      	blx	r2
 7cc:	4601      	mov	r1, r0
 7ce:	f8db 0000 	ldr.w	r0, [fp]
 7d2:	4ab8      	ldr	r2, [pc, #736]	@ (ab4 <Java_org_reference_panel_Guard_probe@@Base+0x36c>)
 7d4:	4bb8      	ldr	r3, [pc, #736]	@ (ab8 <Java_org_reference_panel_Guard_probe@@Base+0x370>)
 7d6:	f8d0 6084 	ldr.w	r6, [r0, #132]	@ 0x84
 7da:	447a      	add	r2, pc
 7dc:	447b      	add	r3, pc
 7de:	4658      	mov	r0, fp
 7e0:	47b0      	blx	r6
 7e2:	4602      	mov	r2, r0
 7e4:	2040      	movs	r0, #64	@ 0x40
 7e6:	9000      	str	r0, [sp, #0]
 7e8:	4658      	mov	r0, fp
 7ea:	4641      	mov	r1, r8
 7ec:	462b      	mov	r3, r5
 7ee:	f000 f971 	bl	ad4 <Java_org_reference_panel_Guard_probe@@Base+0x38c>
 7f2:	4605      	mov	r5, r0
 7f4:	f8db 0000 	ldr.w	r0, [fp]
 7f8:	f8d0 1390 	ldr.w	r1, [r0, #912]	@ 0x390
 7fc:	4658      	mov	r0, fp
 7fe:	4788      	blx	r1
 800:	f8db 1000 	ldr.w	r1, [fp]
 804:	b1b0      	cbz	r0, 834 <Java_org_reference_panel_Guard_probe@@Base+0xec>
 806:	6c49      	ldr	r1, [r1, #68]	@ 0x44
 808:	4658      	mov	r0, fp
 80a:	4788      	blx	r1
 80c:	203b      	movs	r0, #59	@ 0x3b
 80e:	9935      	ldr	r1, [sp, #212]	@ 0xd4
 810:	4aaf      	ldr	r2, [pc, #700]	@ (ad0 <Java_org_reference_panel_Guard_probe@@Base+0x388>)
 812:	447a      	add	r2, pc
 814:	6812      	ldr	r2, [r2, #0]
 816:	6812      	ldr	r2, [r2, #0]
 818:	428a      	cmp	r2, r1
 81a:	bf01      	itttt	eq
 81c:	b036      	addeq	sp, #216	@ 0xd8
 81e:	ecbd 8b0a 	vpopeq	{d8-d12}
 822:	ecbd eb04 	vpopeq	{d14-d15}
 826:	b001      	addeq	sp, #4
 828:	bf04      	itt	eq
 82a:	e8bd 0f00 	ldmiaeq.w	sp!, {r8, r9, sl, fp}
 82e:	bdf0      	popeq	{r4, r5, r6, r7, pc}
 830:	f000 ea96 	blx	d60 <Java_org_reference_panel_Guard_probe@@Base+0x618>
 834:	6fca      	ldr	r2, [r1, #124]	@ 0x7c
 836:	4658      	mov	r0, fp
 838:	4629      	mov	r1, r5
 83a:	4790      	blx	r2
 83c:	4601      	mov	r1, r0
 83e:	f8db 0000 	ldr.w	r0, [fp]
 842:	4a9e      	ldr	r2, [pc, #632]	@ (abc <Java_org_reference_panel_Guard_probe@@Base+0x374>)
 844:	4b9e      	ldr	r3, [pc, #632]	@ (ac0 <Java_org_reference_panel_Guard_probe@@Base+0x378>)
 846:	f8d0 6178 	ldr.w	r6, [r0, #376]	@ 0x178
 84a:	447a      	add	r2, pc
 84c:	447b      	add	r3, pc
 84e:	4658      	mov	r0, fp
 850:	47b0      	blx	r6
 852:	4602      	mov	r2, r0
 854:	f8db 0000 	ldr.w	r0, [fp]
 858:	4629      	mov	r1, r5
 85a:	f8d0 317c 	ldr.w	r3, [r0, #380]	@ 0x17c
 85e:	4658      	mov	r0, fp
 860:	4798      	blx	r3
 862:	2800      	cmp	r0, #0
 864:	d0d2      	beq.n	80c <Java_org_reference_panel_Guard_probe@@Base+0xc4>
 866:	4681      	mov	r9, r0
 868:	f8db 0000 	ldr.w	r0, [fp]
 86c:	4649      	mov	r1, r9
 86e:	f8d0 22ac 	ldr.w	r2, [r0, #684]	@ 0x2ac
 872:	4658      	mov	r0, fp
 874:	4790      	blx	r2
 876:	4604      	mov	r4, r0
 878:	f8db 0000 	ldr.w	r0, [fp]
 87c:	4649      	mov	r1, r9
 87e:	f8d0 22ac 	ldr.w	r2, [r0, #684]	@ 0x2ac
 882:	4658      	mov	r0, fp
 884:	4790      	blx	r2
 886:	2800      	cmp	r0, #0
 888:	4601      	mov	r1, r0
 88a:	9007      	str	r0, [sp, #28]
 88c:	f340 80f2 	ble.w	a74 <Java_org_reference_panel_Guard_probe@@Base+0x32c>
 890:	ae18      	add	r6, sp, #96	@ 0x60
 892:	9402      	str	r4, [sp, #8]
 894:	f106 0010 	add.w	r0, r6, #16
 898:	9003      	str	r0, [sp, #12]
 89a:	2001      	movs	r0, #1
 89c:	ef85 c81a 	vmov.i16	d12, #90	@ 0x005a
 8a0:	9008      	str	r0, [sp, #32]
 8a2:	ff87 e05f 	vmov.i32	q7, #255	@ 0x000000ff
 8a6:	4887      	ldr	r0, [pc, #540]	@ (ac4 <Java_org_reference_panel_Guard_probe@@Base+0x37c>)
 8a8:	ad10      	add	r5, sp, #64	@ 0x40
 8aa:	f10d 0a34 	add.w	sl, sp, #52	@ 0x34
 8ae:	f04f 0800 	mov.w	r8, #0
 8b2:	4478      	add	r0, pc
 8b4:	9006      	str	r0, [sp, #24]
 8b6:	4884      	ldr	r0, [pc, #528]	@ (ac8 <Java_org_reference_panel_Guard_probe@@Base+0x380>)
 8b8:	f8cd 9010 	str.w	r9, [sp, #16]
 8bc:	4478      	add	r0, pc
 8be:	9005      	str	r0, [sp, #20]
 8c0:	a06f      	add	r0, pc, #444	@ (adr r0, a80 <Java_org_reference_panel_Guard_probe@@Base+0x338>)
 8c2:	f920 8acf 	vld1.64	{d8-d9}, [r0]
 8c6:	a072      	add	r0, pc, #456	@ (adr r0, a90 <Java_org_reference_panel_Guard_probe@@Base+0x348>)
 8c8:	f920 aacf 	vld1.64	{d10-d11}, [r0]
 8cc:	f8db 0000 	ldr.w	r0, [fp]
 8d0:	4649      	mov	r1, r9
 8d2:	4642      	mov	r2, r8
 8d4:	f8d0 32b4 	ldr.w	r3, [r0, #692]	@ 0x2b4
 8d8:	4658      	mov	r0, fp
 8da:	4798      	blx	r3
 8dc:	464c      	mov	r4, r9
 8de:	4681      	mov	r9, r0
 8e0:	f8db 0000 	ldr.w	r0, [fp]
 8e4:	4649      	mov	r1, r9
 8e6:	6fc2      	ldr	r2, [r0, #124]	@ 0x7c
 8e8:	4658      	mov	r0, fp
 8ea:	4790      	blx	r2
 8ec:	4601      	mov	r1, r0
 8ee:	f8db 0000 	ldr.w	r0, [fp]
 8f2:	e9dd 3205 	ldrd	r3, r2, [sp, #20]
 8f6:	f8d0 c084 	ldr.w	ip, [r0, #132]	@ 0x84
 8fa:	4658      	mov	r0, fp
 8fc:	47e0      	blx	ip
 8fe:	4602      	mov	r2, r0
 900:	4649      	mov	r1, r9
 902:	4658      	mov	r0, fp
 904:	46a1      	mov	r9, r4
 906:	f000 f8e5 	bl	ad4 <Java_org_reference_panel_Guard_probe@@Base+0x38c>
 90a:	2800      	cmp	r0, #0
 90c:	f000 809c 	beq.w	a48 <Java_org_reference_panel_Guard_probe@@Base+0x300>
 910:	4604      	mov	r4, r0
 912:	f8db 0000 	ldr.w	r0, [fp]
 916:	4621      	mov	r1, r4
 918:	f8cd 8030 	str.w	r8, [sp, #48]	@ 0x30
 91c:	f8d0 22ac 	ldr.w	r2, [r0, #684]	@ 0x2ac
 920:	4658      	mov	r0, fp
 922:	4790      	blx	r2
 924:	4680      	mov	r8, r0
 926:	f8db 0000 	ldr.w	r0, [fp]
 92a:	4621      	mov	r1, r4
 92c:	2200      	movs	r2, #0
 92e:	940b      	str	r4, [sp, #44]	@ 0x2c
 930:	f8d0 32e0 	ldr.w	r3, [r0, #736]	@ 0x2e0
 934:	4658      	mov	r0, fp
 936:	4798      	blx	r3
 938:	4601      	mov	r1, r0
 93a:	9803      	ldr	r0, [sp, #12]
 93c:	4634      	mov	r4, r6
 93e:	f04f 0900 	mov.w	r9, #0
 942:	4642      	mov	r2, r8
 944:	f8cd 90c8 	str.w	r9, [sp, #200]	@ 0xc8
 948:	f900 8acf 	vst1.64	{d8-d9}, [r0]
 94c:	2020      	movs	r0, #32
 94e:	f904 aa80 	vst1.32	{d10-d11}, [r4], r0
 952:	4630      	mov	r0, r6
 954:	e9c4 9900 	strd	r9, r9, [r4]
 958:	910a      	str	r1, [sp, #40]	@ 0x28
 95a:	f000 f8df 	bl	b1c <Java_org_reference_panel_Guard_probe@@Base+0x3d4>
 95e:	e9d4 8000 	ldrd	r8, r0, [r4]
 962:	f1a7 01f1 	sub.w	r1, r7, #241	@ 0xf1
 966:	2201      	movs	r2, #1
 968:	9009      	str	r0, [sp, #36]	@ 0x24
 96a:	2080      	movs	r0, #128	@ 0x80
 96c:	f807 0cf1 	strb.w	r0, [r7, #-241]
 970:	4630      	mov	r0, r6
 972:	f000 f8d3 	bl	b1c <Java_org_reference_panel_Guard_probe@@Base+0x3d4>
 976:	9832      	ldr	r0, [sp, #200]	@ 0xc8
 978:	f1a7 04f2 	sub.w	r4, r7, #242	@ 0xf2
 97c:	f807 9cf2 	strb.w	r9, [r7, #-242]
 980:	2838      	cmp	r0, #56	@ 0x38
 982:	d006      	beq.n	992 <Java_org_reference_panel_Guard_probe@@Base+0x24a>
 984:	4630      	mov	r0, r6
 986:	4621      	mov	r1, r4
 988:	2201      	movs	r2, #1
 98a:	f000 f8c7 	bl	b1c <Java_org_reference_panel_Guard_probe@@Base+0x3d4>
 98e:	9832      	ldr	r0, [sp, #200]	@ 0xc8
 990:	e7f6      	b.n	980 <Java_org_reference_panel_Guard_probe@@Base+0x238>
 992:	9809      	ldr	r0, [sp, #36]	@ 0x24
 994:	4641      	mov	r1, r8
 996:	00cc      	lsls	r4, r1, #3
 998:	f04f 0938 	mov.w	r9, #56	@ 0x38
 99c:	00c0      	lsls	r0, r0, #3
 99e:	ea40 7858 	orr.w	r8, r0, r8, lsr #29
 9a2:	f1c9 0120 	rsb	r1, r9, #32
 9a6:	fa24 f009 	lsr.w	r0, r4, r9
 9aa:	2201      	movs	r2, #1
 9ac:	fa08 f101 	lsl.w	r1, r8, r1
 9b0:	4308      	orrs	r0, r1
 9b2:	f1b9 0120 	subs.w	r1, r9, #32
 9b6:	bf58      	it	pl
 9b8:	fa28 f001 	lsrpl.w	r0, r8, r1
 9bc:	f88d 0040 	strb.w	r0, [sp, #64]	@ 0x40
 9c0:	4630      	mov	r0, r6
 9c2:	4629      	mov	r1, r5
 9c4:	f000 f8aa 	bl	b1c <Java_org_reference_panel_Guard_probe@@Base+0x3d4>
 9c8:	f1a9 0908 	sub.w	r9, r9, #8
 9cc:	f119 0008 	adds.w	r0, r9, #8
 9d0:	d1e7      	bne.n	9a2 <Java_org_reference_panel_Guard_probe@@Base+0x25a>
 9d2:	2000      	movs	r0, #0
 9d4:	5831      	ldr	r1, [r6, r0]
 9d6:	ba09      	rev	r1, r1
 9d8:	5029      	str	r1, [r5, r0]
 9da:	3004      	adds	r0, #4
 9dc:	2820      	cmp	r0, #32
 9de:	d1f9      	bne.n	9d4 <Java_org_reference_panel_Guard_probe@@Base+0x28c>
 9e0:	f8db 0000 	ldr.w	r0, [fp]
 9e4:	2302      	movs	r3, #2
 9e6:	e9dd 210a 	ldrd	r2, r1, [sp, #40]	@ 0x28
 9ea:	f8d0 4300 	ldr.w	r4, [r0, #768]	@ 0x300
 9ee:	4658      	mov	r0, fp
 9f0:	47a0      	blx	r4
 9f2:	efc0 0050 	vmov.i32	q8, #0	@ 0x00000000
 9f6:	f8dd 9010 	ldr.w	r9, [sp, #16]
 9fa:	f8dd 8030 	ldr.w	r8, [sp, #48]	@ 0x30
 9fe:	ab0e      	add	r3, sp, #56	@ 0x38
 a00:	2000      	movs	r0, #0
 a02:	4932      	ldr	r1, [pc, #200]	@ (acc <Java_org_reference_panel_Guard_probe@@Base+0x384>)
 a04:	582a      	ldr	r2, [r5, r0]
 a06:	4479      	add	r1, pc
 a08:	5809      	ldr	r1, [r1, r0]
 a0a:	3004      	adds	r0, #4
 a0c:	920e      	str	r2, [sp, #56]	@ 0x38
 a0e:	f9e3 283f 	vld1.32	{d18[0]}, [r3 :32]
 a12:	910d      	str	r1, [sp, #52]	@ 0x34
 a14:	2820      	cmp	r0, #32
 a16:	f9ea 383f 	vld1.32	{d19[0]}, [sl :32]
 a1a:	ffc8 4a32 	vmovl.u8	q10, d18
 a1e:	ffc8 2a33 	vmovl.u8	q9, d19
 a22:	ff44 21b2 	veor	d18, d20, d18
 a26:	ff42 219c 	veor	d18, d18, d12
 a2a:	ffd0 2a32 	vmovl.u16	q9, d18
 a2e:	ef42 21de 	vand	q9, q9, q7
 a32:	ef60 08e2 	vadd.i32	q8, q8, q9
 a36:	d1e4      	bne.n	a02 <Java_org_reference_panel_Guard_probe@@Base+0x2ba>
 a38:	ef60 08a1 	vadd.i32	d16, d16, d17
 a3c:	ee30 0b90 	vmov.32	r0, d16[1]
 a40:	ee10 1b90 	vmov.32	r1, d16[0]
 a44:	1840      	adds	r0, r0, r1
 a46:	d00a      	beq.n	a5e <Java_org_reference_panel_Guard_probe@@Base+0x316>
 a48:	9807      	ldr	r0, [sp, #28]
 a4a:	f108 0801 	add.w	r8, r8, #1
 a4e:	4580      	cmp	r8, r0
 a50:	f04f 0000 	mov.w	r0, #0
 a54:	bfb8      	it	lt
 a56:	2001      	movlt	r0, #1
 a58:	9008      	str	r0, [sp, #32]
 a5a:	f47f af37 	bne.w	8cc <Java_org_reference_panel_Guard_probe@@Base+0x184>
 a5e:	9902      	ldr	r1, [sp, #8]
 a60:	2025      	movs	r0, #37	@ 0x25
 a62:	4348      	muls	r0, r1
 a64:	2101      	movs	r1, #1
 a66:	f361 109f 	bfi	r0, r1, #6, #26
 a6a:	9908      	ldr	r1, [sp, #32]
 a6c:	07c9      	lsls	r1, r1, #31
 a6e:	bf18      	it	ne
 a70:	2000      	movne	r0, #0
 a72:	e6cc      	b.n	80e <Java_org_reference_panel_Guard_probe@@Base+0xc6>
 a74:	2025      	movs	r0, #37	@ 0x25
 a76:	2101      	movs	r1, #1
 a78:	4360      	muls	r0, r4
 a7a:	f361 109f 	bfi	r0, r1, #6, #26
 a7e:	e6c6      	b.n	80e <Java_org_reference_panel_Guard_probe@@Base+0xc6>
 a80:	527f      	strh	r7, [r7, r1]
 a82:	510e      	str	r6, [r1, r4]
 a84:	688c      	ldr	r4, [r1, #8]
 a86:	9b05      	ldr	r3, [sp, #20]
 a88:	d9ab      	bls.n	9e2 <Java_org_reference_panel_Guard_probe@@Base+0x29a>
 a8a:	1f83      	subs	r3, r0, #6
 a8c:	cd19      	ldmia	r5!, {r0, r3, r4}
 a8e:	5be0      	ldrh	r0, [r4, r7]
 a90:	e667      	b.n	762 <Java_org_reference_panel_Guard_probe@@Base+0x1a>
 a92:	6a09      	ldr	r1, [r1, #32]
 a94:	ae85      	add	r6, sp, #532	@ 0x214
 a96:	bb67      	cbnz	r7, af2 <Java_org_reference_panel_Guard_probe@@Base+0x3aa>
 a98:	f372 3c6e 			@ <UNDEFINED> instruction: 0xf3723c6e
 a9c:	f53a a54f 	bmi.w	fff7b53e <Java_org_reference_panel_Guard_probe@@Base+0xfff7adf6>
 aa0:	16f6      	asrs	r6, r6, #27
 aa2:	0000      	movs	r0, r0
 aa4:	fdc7 ffff 	stc2l	15, cr15, [r7, #1020]	@ 0x3fc
 aa8:	fd9f ffff 	ldc2	15, cr15, [pc, #1020]	@ ea8 <Java_org_reference_panel_Guard_probe@@Base+0x760>
 aac:	fdf7 ffff 	ldc2l	15, cr15, [r7, #1020]!	@ 0x3fc
 ab0:	fd2c ffff 	stc2	15, cr15, [ip, #-1020]!	@ 0xfffffc04
 ab4:	fd33 ffff 	ldc2	15, cr15, [r3, #-1020]!	@ 0xfffffc04
 ab8:	fd83 ffff 	stc2	15, cr15, [r3, #1020]	@ 0x3fc
 abc:	fcd2 ffff 	ldc2l	15, cr15, [r2], {255}	@ 0xff
 ac0:	fca1 ffff 	stc2	15, cr15, [r1], #1020	@ 0x3fc
 ac4:	fc2f ffff 	stc2	15, cr15, [pc], #-1020	@ 6cc <Java_org_reference_panel_Guard_resolve@@Base-0x58>
 ac8:	fce8 ffff 	stc2l	15, cr15, [r8], #1020	@ 0x3fc
 acc:	fba3 ffff 			@ <UNDEFINED> instruction: 0xfba3ffff
 ad0:	1646      	asrs	r6, r0, #25
 ad2:	0000      	movs	r0, r0
 ad4:	b081      	sub	sp, #4
 ad6:	b580      	push	{r7, lr}
 ad8:	466f      	mov	r7, sp
 ada:	b083      	sub	sp, #12
 adc:	60bb      	str	r3, [r7, #8]
 ade:	4b0d      	ldr	r3, [pc, #52]	@ (b14 <Java_org_reference_panel_Guard_probe@@Base+0x3cc>)
 ae0:	447b      	add	r3, pc
 ae2:	681b      	ldr	r3, [r3, #0]
 ae4:	681b      	ldr	r3, [r3, #0]
 ae6:	9302      	str	r3, [sp, #8]
 ae8:	6803      	ldr	r3, [r0, #0]
 aea:	f8d3 c08c 	ldr.w	ip, [r3, #140]	@ 0x8c
 aee:	f107 0308 	add.w	r3, r7, #8
 af2:	9301      	str	r3, [sp, #4]
 af4:	47e0      	blx	ip
 af6:	9902      	ldr	r1, [sp, #8]
 af8:	4a07      	ldr	r2, [pc, #28]	@ (b18 <Java_org_reference_panel_Guard_probe@@Base+0x3d0>)
 afa:	447a      	add	r2, pc
 afc:	6812      	ldr	r2, [r2, #0]
 afe:	6812      	ldr	r2, [r2, #0]
 b00:	428a      	cmp	r2, r1
 b02:	bf01      	itttt	eq
 b04:	b003      	addeq	sp, #12
 b06:	e8bd 4080 	ldmiaeq.w	sp!, {r7, lr}
 b0a:	b001      	addeq	sp, #4
 b0c:	4770      	bxeq	lr
 b0e:	f000 e928 	blx	d60 <Java_org_reference_panel_Guard_probe@@Base+0x618>
 b12:	bf00      	nop
 b14:	1378      	asrs	r0, r7, #13
 b16:	0000      	movs	r0, r0
 b18:	135e      	asrs	r6, r3, #13
 b1a:	0000      	movs	r0, r0
 b1c:	b5f0      	push	{r4, r5, r6, r7, lr}
 b1e:	af03      	add	r7, sp, #12
 b20:	e92d 0f00 	stmdb	sp!, {r8, r9, sl, fp}
 b24:	b0d1      	sub	sp, #324	@ 0x144
 b26:	4680      	mov	r8, r0
 b28:	4874      	ldr	r0, [pc, #464]	@ (cfc <Java_org_reference_panel_Guard_probe@@Base+0x5b4>)
 b2a:	460d      	mov	r5, r1
 b2c:	4478      	add	r0, pc
 b2e:	6800      	ldr	r0, [r0, #0]
 b30:	6800      	ldr	r0, [r0, #0]
 b32:	9050      	str	r0, [sp, #320]	@ 0x140
 b34:	e9d8 0108 	ldrd	r0, r1, [r8, #32]
 b38:	1880      	adds	r0, r0, r2
 b3a:	f141 0100 	adc.w	r1, r1, #0
 b3e:	2a00      	cmp	r2, #0
 b40:	e9c8 0108 	strd	r0, r1, [r8, #32]
 b44:	f000 80cc 	beq.w	ce0 <Java_org_reference_panel_Guard_probe@@Base+0x598>
 b48:	f8d8 1068 	ldr.w	r1, [r8, #104]	@ 0x68
 b4c:	f108 0a28 	add.w	sl, r8, #40	@ 0x28
 b50:	f10d 0940 	add.w	r9, sp, #64	@ 0x40
 b54:	4614      	mov	r4, r2
 b56:	e9cd a801 	strd	sl, r8, [sp, #4]
 b5a:	f1c1 0640 	rsb	r6, r1, #64	@ 0x40
 b5e:	eb0a 0001 	add.w	r0, sl, r1
 b62:	42a6      	cmp	r6, r4
 b64:	bf28      	it	cs
 b66:	4626      	movcs	r6, r4
 b68:	4629      	mov	r1, r5
 b6a:	4632      	mov	r2, r6
 b6c:	f000 e8cc 	blx	d08 <Java_org_reference_panel_Guard_probe@@Base+0x5c0>
 b70:	f8d8 0068 	ldr.w	r0, [r8, #104]	@ 0x68
 b74:	1981      	adds	r1, r0, r6
 b76:	f8c8 1068 	str.w	r1, [r8, #104]	@ 0x68
 b7a:	2940      	cmp	r1, #64	@ 0x40
 b7c:	f040 80ac 	bne.w	cd8 <Java_org_reference_panel_Guard_probe@@Base+0x590>
 b80:	2000      	movs	r0, #0
 b82:	e9cd 540c 	strd	r5, r4, [sp, #48]	@ 0x30
 b86:	eb08 0100 	add.w	r1, r8, r0
 b8a:	6a89      	ldr	r1, [r1, #40]	@ 0x28
 b8c:	ba09      	rev	r1, r1
 b8e:	f849 1000 	str.w	r1, [r9, r0]
 b92:	3004      	adds	r0, #4
 b94:	2840      	cmp	r0, #64	@ 0x40
 b96:	d1f6      	bne.n	b86 <Java_org_reference_panel_Guard_probe@@Base+0x43e>
 b98:	9910      	ldr	r1, [sp, #64]	@ 0x40
 b9a:	2000      	movs	r0, #0
 b9c:	960b      	str	r6, [sp, #44]	@ 0x2c
 b9e:	eb09 0280 	add.w	r2, r9, r0, lsl #2
 ba2:	3001      	adds	r0, #1
 ba4:	2830      	cmp	r0, #48	@ 0x30
 ba6:	6b95      	ldr	r5, [r2, #56]	@ 0x38
 ba8:	6853      	ldr	r3, [r2, #4]
 baa:	6a56      	ldr	r6, [r2, #36]	@ 0x24
 bac:	ea4f 4475 	mov.w	r4, r5, ror #17
 bb0:	ea84 44f5 	eor.w	r4, r4, r5, ror #19
 bb4:	4431      	add	r1, r6
 bb6:	ea84 2595 	eor.w	r5, r4, r5, lsr #10
 bba:	ea4f 14f3 	mov.w	r4, r3, ror #7
 bbe:	ea84 44b3 	eor.w	r4, r4, r3, ror #18
 bc2:	ea84 04d3 	eor.w	r4, r4, r3, lsr #3
 bc6:	4421      	add	r1, r4
 bc8:	4429      	add	r1, r5
 bca:	6411      	str	r1, [r2, #64]	@ 0x40
 bcc:	4619      	mov	r1, r3
 bce:	d1e6      	bne.n	b9e <Java_org_reference_panel_Guard_probe@@Base+0x456>
 bd0:	e9d8 0e00 	ldrd	r0, lr, [r8]
 bd4:	f04f 0900 	mov.w	r9, #0
 bd8:	e9d8 2c02 	ldrd	r2, ip, [r8, #8]
 bdc:	e9d8 6304 	ldrd	r6, r3, [r8, #16]
 be0:	e9d8 4a06 	ldrd	r4, sl, [r8, #24]
 be4:	4601      	mov	r1, r0
 be6:	900a      	str	r0, [sp, #40]	@ 0x28
 be8:	4680      	mov	r8, r0
 bea:	4670      	mov	r0, lr
 bec:	9208      	str	r2, [sp, #32]
 bee:	4610      	mov	r0, r2
 bf0:	4611      	mov	r1, r2
 bf2:	4660      	mov	r0, ip
 bf4:	461a      	mov	r2, r3
 bf6:	4650      	mov	r0, sl
 bf8:	f8cd e024 	str.w	lr, [sp, #36]	@ 0x24
 bfc:	4620      	mov	r0, r4
 bfe:	f8cd c01c 	str.w	ip, [sp, #28]
 c02:	f8cd a00c 	str.w	sl, [sp, #12]
 c06:	4632      	mov	r2, r6
 c08:	9404      	str	r4, [sp, #16]
 c0a:	9305      	str	r3, [sp, #20]
 c0c:	9606      	str	r6, [sp, #24]
 c0e:	4602      	mov	r2, r0
 c10:	483b      	ldr	r0, [pc, #236]	@ (d00 <Java_org_reference_panel_Guard_probe@@Base+0x5b8>)
 c12:	468b      	mov	fp, r1
 c14:	4671      	mov	r1, lr
 c16:	4478      	add	r0, pc
 c18:	4635      	mov	r5, r6
 c1a:	ea8b 0601 	eor.w	r6, fp, r1
 c1e:	46c6      	mov	lr, r8
 c20:	461c      	mov	r4, r3
 c22:	4680      	mov	r8, r0
 c24:	a810      	add	r0, sp, #64	@ 0x40
 c26:	ea0b 0301 	and.w	r3, fp, r1
 c2a:	ea06 060e 	and.w	r6, r6, lr
 c2e:	4073      	eors	r3, r6
 c30:	ea4f 06be 	mov.w	r6, lr, ror #2
 c34:	ea86 367e 	eor.w	r6, r6, lr, ror #13
 c38:	f850 0029 	ldr.w	r0, [r0, r9, lsl #2]
 c3c:	ea86 56be 	eor.w	r6, r6, lr, ror #22
 c40:	4433      	add	r3, r6
 c42:	900f      	str	r0, [sp, #60]	@ 0x3c
 c44:	f858 0029 	ldr.w	r0, [r8, r9, lsl #2]
 c48:	ea05 0804 	and.w	r8, r5, r4
 c4c:	930e      	str	r3, [sp, #56]	@ 0x38
 c4e:	eb08 030a 	add.w	r3, r8, sl
 c52:	ea22 0605 	bic.w	r6, r2, r5
 c56:	4433      	add	r3, r6
 c58:	f109 0901 	add.w	r9, r9, #1
 c5c:	4418      	add	r0, r3
 c5e:	9b0f      	ldr	r3, [sp, #60]	@ 0x3c
 c60:	f1b9 0f40 	cmp.w	r9, #64	@ 0x40
 c64:	4692      	mov	sl, r2
 c66:	4418      	add	r0, r3
 c68:	ea4f 13b5 	mov.w	r3, r5, ror #6
 c6c:	ea83 23f5 	eor.w	r3, r3, r5, ror #11
 c70:	ea83 6375 	eor.w	r3, r3, r5, ror #25
 c74:	4418      	add	r0, r3
 c76:	9b0e      	ldr	r3, [sp, #56]	@ 0x38
 c78:	eb00 060c 	add.w	r6, r0, ip
 c7c:	46dc      	mov	ip, fp
 c7e:	eb03 0800 	add.w	r8, r3, r0
 c82:	4620      	mov	r0, r4
 c84:	462b      	mov	r3, r5
 c86:	d1c2      	bne.n	c0e <Java_org_reference_panel_Guard_probe@@Base+0x4c6>
 c88:	9803      	ldr	r0, [sp, #12]
 c8a:	f10d 0940 	add.w	r9, sp, #64	@ 0x40
 c8e:	f8dd a004 	ldr.w	sl, [sp, #4]
 c92:	eb02 0c00 	add.w	ip, r2, r0
 c96:	9804      	ldr	r0, [sp, #16]
 c98:	1822      	adds	r2, r4, r0
 c9a:	9805      	ldr	r0, [sp, #20]
 c9c:	182b      	adds	r3, r5, r0
 c9e:	9806      	ldr	r0, [sp, #24]
 ca0:	4406      	add	r6, r0
 ca2:	9807      	ldr	r0, [sp, #28]
 ca4:	eb0b 0500 	add.w	r5, fp, r0
 ca8:	9808      	ldr	r0, [sp, #32]
 caa:	4401      	add	r1, r0
 cac:	9809      	ldr	r0, [sp, #36]	@ 0x24
 cae:	eb0e 0400 	add.w	r4, lr, r0
 cb2:	980a      	ldr	r0, [sp, #40]	@ 0x28
 cb4:	4440      	add	r0, r8
 cb6:	f8dd 8008 	ldr.w	r8, [sp, #8]
 cba:	e9c8 0400 	strd	r0, r4, [r8]
 cbe:	f108 0008 	add.w	r0, r8, #8
 cc2:	c062      	stmia	r0!, {r1, r5, r6}
 cc4:	2100      	movs	r1, #0
 cc6:	e9dd 540c 	ldrd	r5, r4, [sp, #48]	@ 0x30
 cca:	9e0b      	ldr	r6, [sp, #44]	@ 0x2c
 ccc:	e9c8 3205 	strd	r3, r2, [r8, #20]
 cd0:	f8c8 c01c 	str.w	ip, [r8, #28]
 cd4:	f8c8 1068 	str.w	r1, [r8, #104]	@ 0x68
 cd8:	1ba4      	subs	r4, r4, r6
 cda:	4435      	add	r5, r6
 cdc:	f47f af3d 	bne.w	b5a <Java_org_reference_panel_Guard_probe@@Base+0x412>
 ce0:	9850      	ldr	r0, [sp, #320]	@ 0x140
 ce2:	4908      	ldr	r1, [pc, #32]	@ (d04 <Java_org_reference_panel_Guard_probe@@Base+0x5bc>)
 ce4:	4479      	add	r1, pc
 ce6:	6809      	ldr	r1, [r1, #0]
 ce8:	6809      	ldr	r1, [r1, #0]
 cea:	4281      	cmp	r1, r0
 cec:	bf02      	ittt	eq
 cee:	b051      	addeq	sp, #324	@ 0x144
 cf0:	e8bd 0f00 	ldmiaeq.w	sp!, {r8, r9, sl, fp}
 cf4:	bdf0      	popeq	{r4, r5, r6, r7, pc}
 cf6:	f000 e834 	blx	d60 <Java_org_reference_panel_Guard_probe@@Base+0x618>
 cfa:	bf00      	nop
 cfc:	132c      	asrs	r4, r5, #12
 cfe:	0000      	movs	r0, r0
 d00:	f9b6 ffff 	ldrsh.w	pc, [r6, #4095]	@ 0xfff
 d04:	1174      	asrs	r4, r6, #5
 d06:	0000      	movs	r0, r0
 d08:	0018      	movs	r0, r3
 d0a:	Address 0xd0a is out of bounds.


Disassembly of section .plt:

00000d10 <.plt>:
 d10:	e52de004 	push	{lr}		@ (str lr, [sp, #-4]!)
 d14:	e28fe600 	add	lr, pc, #0, 12
 d18:	e28eea01 	add	lr, lr, #4096	@ 0x1000
 d1c:	e5bef14c 	ldr	pc, [lr, #332]!	@ 0x14c
 d20:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d24:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d28:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d2c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d30:	e28fc600 	add	ip, pc, #0, 12
 d34:	e28cca01 	add	ip, ip, #4096	@ 0x1000
 d38:	e5bcf134 	ldr	pc, [ip, #308]!	@ 0x134
 d3c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d40:	e28fc600 	add	ip, pc, #0, 12
 d44:	e28cca01 	add	ip, ip, #4096	@ 0x1000
 d48:	e5bcf128 	ldr	pc, [ip, #296]!	@ 0x128
 d4c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d50:	e28fc600 	add	ip, pc, #0, 12
 d54:	e28cca01 	add	ip, ip, #4096	@ 0x1000
 d58:	e5bcf11c 	ldr	pc, [ip, #284]!	@ 0x11c
 d5c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d60:	e28fc600 	add	ip, pc, #0, 12
 d64:	e28cca01 	add	ip, ip, #4096	@ 0x1000
 d68:	e5bcf110 	ldr	pc, [ip, #272]!	@ 0x110
 d6c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
 d70:	e28fc600 	add	ip, pc, #0, 12
 d74:	e28cca01 	add	ip, ip, #4096	@ 0x1000
 d78:	e5bcf104 	ldr	pc, [ip, #260]!	@ 0x104
 d7c:	d4d4d4d4 	ldrble	sp, [r4], #1236	@ 0x4d4
