
docs/reference-analysis/artifacts/libdhguard.so:     file format elf64-littleaarch64


Disassembly of section .text:

00000000000009a8 <Java_org_reference_panel_Guard_resolve@@Base-0x60>:
     9a8:	d503245f 	bti	c
     9ac:	d503201f 	nop
     9b0:	10024600 	adr	x0, 5270 <memcpy@plt+0x4010>
     9b4:	1400021b 	b	1220 <__cxa_finalize@plt>
     9b8:	d503245f 	bti	c
     9bc:	d65f03c0 	ret
     9c0:	d503245f 	bti	c
     9c4:	17fffffd 	b	9b8 <Java_org_reference_panel_Guard_resolve@@Base-0x50>
     9c8:	d503245f 	bti	c
     9cc:	b4000060 	cbz	x0, 9d8 <Java_org_reference_panel_Guard_resolve@@Base-0x30>
     9d0:	aa0003f0 	mov	x16, x0
     9d4:	d61f0200 	br	x16
     9d8:	d65f03c0 	ret
     9dc:	d503245f 	bti	c
     9e0:	aa0003e1 	mov	x1, x0
     9e4:	d503201f 	nop
     9e8:	10ffff00 	adr	x0, 9c8 <Java_org_reference_panel_Guard_resolve@@Base-0x40>
     9ec:	d503201f 	nop
     9f0:	10024402 	adr	x2, 5270 <memcpy@plt+0x4010>
     9f4:	1400020f 	b	1230 <__cxa_atexit@plt>
     9f8:	d503245f 	bti	c
     9fc:	d503201f 	nop
     a00:	10024383 	adr	x3, 5270 <memcpy@plt+0x4010>
     a04:	1400020f 	b	1240 <__register_atfork@plt>

0000000000000a08 <Java_org_reference_panel_Guard_resolve@@Base>:
     a08:	71004c5f 	cmp	w2, #0x13
     a0c:	54000069 	b.ls	a18 <Java_org_reference_panel_Guard_resolve@@Base+0x10>  // b.plast
     a10:	2a1f03e0 	mov	w0, wzr
     a14:	d65f03c0 	ret
     a18:	d503201f 	nop
     a1c:	10ffdb28 	adr	x8, 580 <Java_org_reference_panel_Guard_resolve@@Base-0x488>
     a20:	5293cf69 	mov	w9, #0x9e7b                	// #40571
     a24:	b8625908 	ldr	w8, [x8, w2, uxtw #2]
     a28:	72b4b869 	movk	w9, #0xa5c3, lsl #16
     a2c:	4a090100 	eor	w0, w8, w9
     a30:	d65f03c0 	ret

0000000000000a34 <Java_org_reference_panel_Guard_probe@@Base>:
     a34:	d10543ff 	sub	sp, sp, #0x150
     a38:	a90f7bfd 	stp	x29, x30, [sp, #240]
     a3c:	a9106ffc 	stp	x28, x27, [sp, #256]
     a40:	a91167fa 	stp	x26, x25, [sp, #272]
     a44:	a9125ff8 	stp	x24, x23, [sp, #288]
     a48:	a91357f6 	stp	x22, x21, [sp, #304]
     a4c:	a9144ff4 	stp	x20, x19, [sp, #320]
     a50:	9103c3fd 	add	x29, sp, #0xf0
     a54:	d53bd057 	mrs	x23, tpidr_el0
     a58:	aa0203e1 	mov	x1, x2
     a5c:	aa0003f3 	mov	x19, x0
     a60:	f94016e8 	ldr	x8, [x23, #40]
     a64:	aa0203f4 	mov	x20, x2
     a68:	f81f03a8 	stur	x8, [x29, #-16]
     a6c:	f9400008 	ldr	x8, [x0]
     a70:	f9407d08 	ldr	x8, [x8, #248]
     a74:	d63f0100 	blr	x8
     a78:	f9400268 	ldr	x8, [x19]
     a7c:	aa0003f5 	mov	x21, x0
     a80:	d503201f 	nop
     a84:	30ffde62 	adr	x2, 651 <Java_org_reference_panel_Guard_resolve@@Base-0x3b7>
     a88:	90000003 	adrp	x3, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     a8c:	9118ac63 	add	x3, x3, #0x62b
     a90:	f9408508 	ldr	x8, [x8, #264]
     a94:	aa1303e0 	mov	x0, x19
     a98:	aa1503e1 	mov	x1, x21
     a9c:	d63f0100 	blr	x8
     aa0:	f9400268 	ldr	x8, [x19]
     aa4:	aa0003f6 	mov	x22, x0
     aa8:	90000002 	adrp	x2, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     aac:	911a6442 	add	x2, x2, #0x699
     ab0:	90000003 	adrp	x3, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     ab4:	91174063 	add	x3, x3, #0x5d0
     ab8:	f9408508 	ldr	x8, [x8, #264]
     abc:	aa1303e0 	mov	x0, x19
     ac0:	aa1503e1 	mov	x1, x21
     ac4:	d63f0100 	blr	x8
     ac8:	aa0003f5 	mov	x21, x0
     acc:	aa1303e0 	mov	x0, x19
     ad0:	aa1403e1 	mov	x1, x20
     ad4:	aa1603e2 	mov	x2, x22
     ad8:	940000ee 	bl	e90 <Java_org_reference_panel_Guard_probe@@Base+0x45c>
     adc:	aa0003f6 	mov	x22, x0
     ae0:	aa1303e0 	mov	x0, x19
     ae4:	aa1403e1 	mov	x1, x20
     ae8:	aa1503e2 	mov	x2, x21
     aec:	940000e9 	bl	e90 <Java_org_reference_panel_Guard_probe@@Base+0x45c>
     af0:	f9400268 	ldr	x8, [x19]
     af4:	aa0003f4 	mov	x20, x0
     af8:	aa1303e0 	mov	x0, x19
     afc:	aa1603e1 	mov	x1, x22
     b00:	f9407d08 	ldr	x8, [x8, #248]
     b04:	d63f0100 	blr	x8
     b08:	f9400268 	ldr	x8, [x19]
     b0c:	aa0003e1 	mov	x1, x0
     b10:	90000002 	adrp	x2, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     b14:	91184442 	add	x2, x2, #0x611
     b18:	90000003 	adrp	x3, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     b1c:	91198c63 	add	x3, x3, #0x663
     b20:	f9408508 	ldr	x8, [x8, #264]
     b24:	aa1303e0 	mov	x0, x19
     b28:	d63f0100 	blr	x8
     b2c:	aa0003e2 	mov	x2, x0
     b30:	aa1303e0 	mov	x0, x19
     b34:	aa1603e1 	mov	x1, x22
     b38:	aa1403e3 	mov	x3, x20
     b3c:	52800804 	mov	w4, #0x40                  	// #64
     b40:	940000d4 	bl	e90 <Java_org_reference_panel_Guard_probe@@Base+0x45c>
     b44:	f9400268 	ldr	x8, [x19]
     b48:	aa0003f4 	mov	x20, x0
     b4c:	aa1303e0 	mov	x0, x19
     b50:	f9439108 	ldr	x8, [x8, #1824]
     b54:	d63f0100 	blr	x8
     b58:	f9400268 	ldr	x8, [x19]
     b5c:	72001c1f 	tst	w0, #0xff
     b60:	54000220 	b.eq	ba4 <Java_org_reference_panel_Guard_probe@@Base+0x170>  // b.none
     b64:	f9404508 	ldr	x8, [x8, #136]
     b68:	aa1303e0 	mov	x0, x19
     b6c:	d63f0100 	blr	x8
     b70:	52800760 	mov	w0, #0x3b                  	// #59
     b74:	f94016e8 	ldr	x8, [x23, #40]
     b78:	f85f03a9 	ldur	x9, [x29, #-16]
     b7c:	eb09011f 	cmp	x8, x9
     b80:	54001861 	b.ne	e8c <Java_org_reference_panel_Guard_probe@@Base+0x458>  // b.any
     b84:	a9544ff4 	ldp	x20, x19, [sp, #320]
     b88:	a95357f6 	ldp	x22, x21, [sp, #304]
     b8c:	a9525ff8 	ldp	x24, x23, [sp, #288]
     b90:	a95167fa 	ldp	x26, x25, [sp, #272]
     b94:	a9506ffc 	ldp	x28, x27, [sp, #256]
     b98:	a94f7bfd 	ldp	x29, x30, [sp, #240]
     b9c:	910543ff 	add	sp, sp, #0x150
     ba0:	d65f03c0 	ret
     ba4:	f9407d08 	ldr	x8, [x8, #248]
     ba8:	aa1303e0 	mov	x0, x19
     bac:	aa1403e1 	mov	x1, x20
     bb0:	d63f0100 	blr	x8
     bb4:	f9400268 	ldr	x8, [x19]
     bb8:	aa0003e1 	mov	x1, x0
     bbc:	90000002 	adrp	x2, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     bc0:	91188042 	add	x2, x2, #0x620
     bc4:	90000003 	adrp	x3, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     bc8:	9117c463 	add	x3, x3, #0x5f1
     bcc:	f9417908 	ldr	x8, [x8, #752]
     bd0:	aa1303e0 	mov	x0, x19
     bd4:	d63f0100 	blr	x8
     bd8:	f9400268 	ldr	x8, [x19]
     bdc:	aa0003e2 	mov	x2, x0
     be0:	aa1303e0 	mov	x0, x19
     be4:	aa1403e1 	mov	x1, x20
     be8:	f9417d08 	ldr	x8, [x8, #760]
     bec:	d63f0100 	blr	x8
     bf0:	b4fffc00 	cbz	x0, b70 <Java_org_reference_panel_Guard_probe@@Base+0x13c>
     bf4:	f9400268 	ldr	x8, [x19]
     bf8:	aa0003f4 	mov	x20, x0
     bfc:	aa1303e0 	mov	x0, x19
     c00:	aa1403e1 	mov	x1, x20
     c04:	f942ad08 	ldr	x8, [x8, #1368]
     c08:	d63f0100 	blr	x8
     c0c:	f9400268 	ldr	x8, [x19]
     c10:	2a0003f5 	mov	w21, w0
     c14:	aa1303e0 	mov	x0, x19
     c18:	aa1403e1 	mov	x1, x20
     c1c:	f942ad08 	ldr	x8, [x8, #1368]
     c20:	d63f0100 	blr	x8
     c24:	7100001f 	cmp	w0, #0x0
     c28:	b90047e0 	str	w0, [sp, #68]
     c2c:	5400126d 	b.le	e78 <Java_org_reference_panel_Guard_probe@@Base+0x444>
     c30:	90000008 	adrp	x8, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     c34:	b90007f5 	str	w21, [sp, #4]
     c38:	52800035 	mov	w21, #0x1                   	// #1
     c3c:	3dc15d00 	ldr	q0, [x8, #1392]
     c40:	90000008 	adrp	x8, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     c44:	f90007f7 	str	x23, [sp, #8]
     c48:	2a1f03f7 	mov	w23, wzr
     c4c:	9101c3f9 	add	x25, sp, #0x70
     c50:	910143f8 	add	x24, sp, #0x50
     c54:	3d800be0 	str	q0, [sp, #32]
     c58:	3dc15900 	ldr	q0, [x8, #1376]
     c5c:	d503201f 	nop
     c60:	30ffd276 	adr	x22, 6ad <Java_org_reference_panel_Guard_resolve@@Base-0x35b>
     c64:	f9001ff4 	str	x20, [sp, #56]
     c68:	3d8007e0 	str	q0, [sp, #16]
     c6c:	f9400268 	ldr	x8, [x19]
     c70:	aa1303e0 	mov	x0, x19
     c74:	aa1403e1 	mov	x1, x20
     c78:	2a1703e2 	mov	w2, w23
     c7c:	f942b508 	ldr	x8, [x8, #1384]
     c80:	d63f0100 	blr	x8
     c84:	f9400268 	ldr	x8, [x19]
     c88:	aa0003fa 	mov	x26, x0
     c8c:	aa1303e0 	mov	x0, x19
     c90:	aa1a03e1 	mov	x1, x26
     c94:	f9407d08 	ldr	x8, [x8, #248]
     c98:	d63f0100 	blr	x8
     c9c:	f9400268 	ldr	x8, [x19]
     ca0:	aa0003e1 	mov	x1, x0
     ca4:	aa1303e0 	mov	x0, x19
     ca8:	90000002 	adrp	x2, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     cac:	91179442 	add	x2, x2, #0x5e5
     cb0:	90000003 	adrp	x3, 0 <Java_org_reference_panel_Guard_resolve@@Base-0xa08>
     cb4:	911aa063 	add	x3, x3, #0x6a8
     cb8:	f9408508 	ldr	x8, [x8, #264]
     cbc:	d63f0100 	blr	x8
     cc0:	aa0003e2 	mov	x2, x0
     cc4:	aa1303e0 	mov	x0, x19
     cc8:	aa1a03e1 	mov	x1, x26
     ccc:	94000071 	bl	e90 <Java_org_reference_panel_Guard_probe@@Base+0x45c>
     cd0:	b4000b80 	cbz	x0, e40 <Java_org_reference_panel_Guard_probe@@Base+0x40c>
     cd4:	f9400268 	ldr	x8, [x19]
     cd8:	aa0003fa 	mov	x26, x0
     cdc:	aa1303e0 	mov	x0, x19
     ce0:	aa1a03e1 	mov	x1, x26
     ce4:	f942ad08 	ldr	x8, [x8, #1368]
     ce8:	d63f0100 	blr	x8
     cec:	f9400268 	ldr	x8, [x19]
     cf0:	2a0003fc 	mov	w28, w0
     cf4:	aa1303e0 	mov	x0, x19
     cf8:	aa1a03e1 	mov	x1, x26
     cfc:	aa1f03e2 	mov	x2, xzr
     d00:	f942e108 	ldr	x8, [x8, #1472]
     d04:	d63f0100 	blr	x8
     d08:	ad4087e0 	ldp	q0, q1, [sp, #16]
     d0c:	aa0003fb 	mov	x27, x0
     d10:	93407f82 	sxtw	x2, w28
     d14:	9101c3e0 	add	x0, sp, #0x70
     d18:	aa1b03e1 	mov	x1, x27
     d1c:	f9004bff 	str	xzr, [sp, #144]
     d20:	ad0383e1 	stp	q1, q0, [sp, #112]
     d24:	f9006fff 	str	xzr, [sp, #216]
     d28:	94000081 	bl	f2c <Java_org_reference_panel_Guard_probe@@Base+0x4f8>
     d2c:	f9404bfc 	ldr	x28, [sp, #144]
     d30:	52801008 	mov	w8, #0x80                  	// #128
     d34:	9101c3e0 	add	x0, sp, #0x70
     d38:	910133e1 	add	x1, sp, #0x4c
     d3c:	52800022 	mov	w2, #0x1                   	// #1
     d40:	390133e8 	strb	w8, [sp, #76]
     d44:	9400007a 	bl	f2c <Java_org_reference_panel_Guard_probe@@Base+0x4f8>
     d48:	f9406fe8 	ldr	x8, [sp, #216]
     d4c:	390123ff 	strb	wzr, [sp, #72]
     d50:	f100e11f 	cmp	x8, #0x38
     d54:	54000100 	b.eq	d74 <Java_org_reference_panel_Guard_probe@@Base+0x340>  // b.none
     d58:	9101c3e0 	add	x0, sp, #0x70
     d5c:	910123e1 	add	x1, sp, #0x48
     d60:	52800022 	mov	w2, #0x1                   	// #1
     d64:	94000072 	bl	f2c <Java_org_reference_panel_Guard_probe@@Base+0x4f8>
     d68:	f9406fe8 	ldr	x8, [sp, #216]
     d6c:	f100e11f 	cmp	x8, #0x38
     d70:	54ffff41 	b.ne	d58 <Java_org_reference_panel_Guard_probe@@Base+0x324>  // b.any
     d74:	d37df39c 	lsl	x28, x28, #3
     d78:	52800714 	mov	w20, #0x38                  	// #56
     d7c:	9ad42788 	lsr	x8, x28, x20
     d80:	9101c3e0 	add	x0, sp, #0x70
     d84:	910143e1 	add	x1, sp, #0x50
     d88:	52800022 	mov	w2, #0x1                   	// #1
     d8c:	390143e8 	strb	w8, [sp, #80]
     d90:	94000067 	bl	f2c <Java_org_reference_panel_Guard_probe@@Base+0x4f8>
     d94:	d1002294 	sub	x20, x20, #0x8
     d98:	b100229f 	cmn	x20, #0x8
     d9c:	54ffff01 	b.ne	d7c <Java_org_reference_panel_Guard_probe@@Base+0x348>  // b.any
     da0:	aa1f03e8 	mov	x8, xzr
     da4:	b8686b29 	ldr	w9, [x25, x8]
     da8:	5ac00929 	rev	w9, w9
     dac:	b8286b09 	str	w9, [x24, x8]
     db0:	91001108 	add	x8, x8, #0x4
     db4:	f100811f 	cmp	x8, #0x20
     db8:	54ffff61 	b.ne	da4 <Java_org_reference_panel_Guard_probe@@Base+0x370>  // b.any
     dbc:	f9400268 	ldr	x8, [x19]
     dc0:	aa1303e0 	mov	x0, x19
     dc4:	aa1a03e1 	mov	x1, x26
     dc8:	aa1b03e2 	mov	x2, x27
     dcc:	52800043 	mov	w3, #0x2                   	// #2
     dd0:	f9430108 	ldr	x8, [x8, #1536]
     dd4:	d63f0100 	blr	x8
     dd8:	6f00e400 	movi	v0.2d, #0x0
     ddc:	6f00e401 	movi	v1.2d, #0x0
     de0:	f9401ff4 	ldr	x20, [sp, #56]
     de4:	6f00e402 	movi	v2.2d, #0x0
     de8:	6f00e403 	movi	v3.2d, #0x0
     dec:	aa1f03e8 	mov	x8, xzr
     df0:	4f02e746 	movi	v6.16b, #0x5a
     df4:	3ce86b04 	ldr	q4, [x24, x8]
     df8:	3ce86ac5 	ldr	q5, [x22, x8]
     dfc:	91004108 	add	x8, x8, #0x10
     e00:	f100811f 	cmp	x8, #0x20
     e04:	6e251c84 	eor	v4.16b, v4.16b, v5.16b
     e08:	6e261c84 	eor	v4.16b, v4.16b, v6.16b
     e0c:	6f08a485 	uxtl2	v5.8h, v4.16b
     e10:	2f08a484 	uxtl	v4.8h, v4.8b
     e14:	6e651063 	uaddw2	v3.4s, v3.4s, v5.8h
     e18:	2e651042 	uaddw	v2.4s, v2.4s, v5.4h
     e1c:	6e641021 	uaddw2	v1.4s, v1.4s, v4.8h
     e20:	2e641000 	uaddw	v0.4s, v0.4s, v4.4h
     e24:	54fffe81 	b.ne	df4 <Java_org_reference_panel_Guard_probe@@Base+0x3c0>  // b.any
     e28:	4ea28400 	add	v0.4s, v0.4s, v2.4s
     e2c:	4ea38421 	add	v1.4s, v1.4s, v3.4s
     e30:	4ea18400 	add	v0.4s, v0.4s, v1.4s
     e34:	4eb1b800 	addv	s0, v0.4s
     e38:	1e260008 	fmov	w8, s0
     e3c:	340000c8 	cbz	w8, e54 <Java_org_reference_panel_Guard_probe@@Base+0x420>
     e40:	b94047e8 	ldr	w8, [sp, #68]
     e44:	110006f7 	add	w23, w23, #0x1
     e48:	6b0802ff 	cmp	w23, w8
     e4c:	1a9fa7f5 	cset	w21, lt	// lt = tstop
     e50:	54fff0e1 	b.ne	c6c <Java_org_reference_panel_Guard_probe@@Base+0x238>  // b.any
     e54:	b94007e9 	ldr	w9, [sp, #4]
     e58:	528004a8 	mov	w8, #0x25                  	// #37
     e5c:	720002bf 	tst	w21, #0x1
     e60:	f94007f7 	ldr	x23, [sp, #8]
     e64:	1b087d28 	mul	w8, w9, w8
     e68:	52800809 	mov	w9, #0x40                  	// #64
     e6c:	33001509 	bfxil	w9, w8, #0, #6
     e70:	1a8913e0 	csel	w0, wzr, w9, ne	// ne = any
     e74:	17ffff40 	b	b74 <Java_org_reference_panel_Guard_probe@@Base+0x140>
     e78:	528004a8 	mov	w8, #0x25                  	// #37
     e7c:	52800800 	mov	w0, #0x40                  	// #64
     e80:	1b087ea8 	mul	w8, w21, w8
     e84:	33001500 	bfxil	w0, w8, #0, #6
     e88:	17ffff3b 	b	b74 <Java_org_reference_panel_Guard_probe@@Base+0x140>
     e8c:	940000f1 	bl	1250 <__stack_chk_fail@plt>
     e90:	d10483ff 	sub	sp, sp, #0x120
     e94:	a9107bfd 	stp	x29, x30, [sp, #256]
     e98:	a9114ffc 	stp	x28, x19, [sp, #272]
     e9c:	910403fd 	add	x29, sp, #0x100
     ea0:	a93893a3 	stp	x3, x4, [x29, #-120]
     ea4:	d101e3a9 	sub	x9, x29, #0x78
     ea8:	910003ea 	mov	x10, sp
     eac:	a9399ba5 	stp	x5, x6, [x29, #-104]
     eb0:	9102014a 	add	x10, x10, #0x80
     eb4:	d10143a3 	sub	x3, x29, #0x50
     eb8:	f81a83a7 	stur	x7, [x29, #-88]
     ebc:	ad0007e0 	stp	q0, q1, [sp]
     ec0:	ad010fe2 	stp	q2, q3, [sp, #32]
     ec4:	ad0217e4 	stp	q4, q5, [sp, #64]
     ec8:	ad031fe6 	stp	q6, q7, [sp, #96]
     ecc:	d53bd053 	mrs	x19, tpidr_el0
     ed0:	f9401668 	ldr	x8, [x19, #40]
     ed4:	f81f83a8 	stur	x8, [x29, #-8]
     ed8:	9100a128 	add	x8, x9, #0x28
     edc:	910083a9 	add	x9, x29, #0x20
     ee0:	a93da3a9 	stp	x9, x8, [x29, #-40]
     ee4:	928004e8 	mov	x8, #0xffffffffffffffd8    	// #-40
     ee8:	d100a3a9 	sub	x9, x29, #0x28
     eec:	f2dff008 	movk	x8, #0xff80, lsl #32
     ef0:	a93ea3aa 	stp	x10, x8, [x29, #-24]
     ef4:	f9400008 	ldr	x8, [x0]
     ef8:	ad400520 	ldp	q0, q1, [x9]
     efc:	f9408d08 	ldr	x8, [x8, #280]
     f00:	ad3d87a0 	stp	q0, q1, [x29, #-80]
     f04:	d63f0100 	blr	x8
     f08:	f9401668 	ldr	x8, [x19, #40]
     f0c:	f85f83a9 	ldur	x9, [x29, #-8]
     f10:	eb09011f 	cmp	x8, x9
     f14:	540000a1 	b.ne	f28 <Java_org_reference_panel_Guard_probe@@Base+0x4f4>  // b.any
     f18:	a9514ffc 	ldp	x28, x19, [sp, #272]
     f1c:	a9507bfd 	ldp	x29, x30, [sp, #256]
     f20:	910483ff 	add	sp, sp, #0x120
     f24:	d65f03c0 	ret
     f28:	940000ca 	bl	1250 <__stack_chk_fail@plt>
     f2c:	d10603ff 	sub	sp, sp, #0x180
     f30:	a9127bfd 	stp	x29, x30, [sp, #288]
     f34:	a9136ffc 	stp	x28, x27, [sp, #304]
     f38:	a91467fa 	stp	x26, x25, [sp, #320]
     f3c:	a9155ff8 	stp	x24, x23, [sp, #336]
     f40:	a91657f6 	stp	x22, x21, [sp, #352]
     f44:	a9174ff4 	stp	x20, x19, [sp, #368]
     f48:	910483fd 	add	x29, sp, #0x120
     f4c:	d53bd048 	mrs	x8, tpidr_el0
     f50:	f90007e8 	str	x8, [sp, #8]
     f54:	f9401508 	ldr	x8, [x8, #40]
     f58:	f81f03a8 	stur	x8, [x29, #-16]
     f5c:	f9401008 	ldr	x8, [x0, #32]
     f60:	8b020108 	add	x8, x8, x2
     f64:	f9001008 	str	x8, [x0, #32]
     f68:	b40012e2 	cbz	x2, 11c4 <Java_org_reference_panel_Guard_probe@@Base+0x790>
     f6c:	f940340e 	ldr	x14, [x0, #104]
     f70:	aa0203f3 	mov	x19, x2
     f74:	aa0103f4 	mov	x20, x1
     f78:	aa0003f5 	mov	x21, x0
     f7c:	9100a018 	add	x24, x0, #0x28
     f80:	52800817 	mov	w23, #0x40                  	// #64
     f84:	910043fa 	add	x26, sp, #0x10
     f88:	d503201f 	nop
     f8c:	10ffba3b 	adr	x27, 6d0 <Java_org_reference_panel_Guard_resolve@@Base-0x338>
     f90:	cb0e02e8 	sub	x8, x23, x14
     f94:	8b0e0300 	add	x0, x24, x14
     f98:	aa1403e1 	mov	x1, x20
     f9c:	eb13011f 	cmp	x8, x19
     fa0:	9a933116 	csel	x22, x8, x19, cc	// cc = lo, ul, last
     fa4:	aa1603e2 	mov	x2, x22
     fa8:	940000ae 	bl	1260 <memcpy@plt>
     fac:	f94036a8 	ldr	x8, [x21, #104]
     fb0:	8b16010e 	add	x14, x8, x22
     fb4:	f10101df 	cmp	x14, #0x40
     fb8:	f90036ae 	str	x14, [x21, #104]
     fbc:	54000fe1 	b.ne	11b8 <Java_org_reference_panel_Guard_probe@@Base+0x784>  // b.any
     fc0:	4c400300 	ld4	{v0.16b-v3.16b}, [x24]
     fc4:	aa1f03e8 	mov	x8, xzr
     fc8:	2f08a424 	uxtl	v4.8h, v1.8b
     fcc:	2f08a445 	uxtl	v5.8h, v2.8b
     fd0:	2f08a406 	uxtl	v6.8h, v0.8b
     fd4:	6f08a407 	uxtl2	v7.8h, v0.16b
     fd8:	6f08a430 	uxtl2	v16.8h, v1.16b
     fdc:	6f08a451 	uxtl2	v17.8h, v2.16b
     fe0:	2f08a474 	uxtl	v20.8h, v3.8b
     fe4:	6f08a460 	uxtl2	v0.8h, v3.16b
     fe8:	2e613892 	shll	v18.4s, v4.4h, #16
     fec:	2f18a4b3 	ushll	v19.4s, v5.4h, #8
     ff0:	6f10a4d5 	uxtl2	v21.4s, v6.8h
     ff4:	6f10a4f6 	uxtl2	v22.4s, v7.8h
     ff8:	2f10a4c6 	uxtl	v6.4s, v6.4h
     ffc:	6e613a17 	shll2	v23.4s, v16.8h, #16
    1000:	2f10a4e7 	uxtl	v7.4s, v7.4h
    1004:	2e613a10 	shll	v16.4s, v16.4h, #16
    1008:	6e613884 	shll2	v4.4s, v4.8h, #16
    100c:	4eb31e52 	orr	v18.16b, v18.16b, v19.16b
    1010:	6f18a633 	ushll2	v19.4s, v17.8h, #8
    1014:	2f18a631 	ushll	v17.4s, v17.4h, #8
    1018:	6f18a4a5 	ushll2	v5.4s, v5.8h, #8
    101c:	4f3854c6 	shl	v6.4s, v6.4s, #24
    1020:	4f3856c1 	shl	v1.4s, v22.4s, #24
    1024:	4f3854e7 	shl	v7.4s, v7.4s, #24
    1028:	2f10a683 	uxtl	v3.4s, v20.4h
    102c:	4eb31ee2 	orr	v2.16b, v23.16b, v19.16b
    1030:	4eb11e10 	orr	v16.16b, v16.16b, v17.16b
    1034:	4f3856b1 	shl	v17.4s, v21.4s, #24
    1038:	4ea51c84 	orr	v4.16b, v4.16b, v5.16b
    103c:	4ea61e45 	orr	v5.16b, v18.16b, v6.16b
    1040:	6f10a406 	uxtl2	v6.4s, v0.8h
    1044:	2f10a400 	uxtl	v0.4s, v0.4h
    1048:	4ea11c41 	orr	v1.16b, v2.16b, v1.16b
    104c:	4ea71e02 	orr	v2.16b, v16.16b, v7.16b
    1050:	6f10a687 	uxtl2	v7.4s, v20.8h
    1054:	4eb11c84 	orr	v4.16b, v4.16b, v17.16b
    1058:	4ea31ca3 	orr	v3.16b, v5.16b, v3.16b
    105c:	4ea01c40 	orr	v0.16b, v2.16b, v0.16b
    1060:	4ea61c21 	orr	v1.16b, v1.16b, v6.16b
    1064:	4ea71c82 	orr	v2.16b, v4.16b, v7.16b
    1068:	ad0187e0 	stp	q0, q1, [sp, #48]
    106c:	ad008be3 	stp	q3, q2, [sp, #16]
    1070:	b94013e9 	ldr	w9, [sp, #16]
    1074:	8b08034a 	add	x10, x26, x8
    1078:	91001108 	add	x8, x8, #0x4
    107c:	b940054b 	ldr	w11, [x10, #4]
    1080:	b940394c 	ldr	w12, [x10, #56]
    1084:	b940254f 	ldr	w15, [x10, #36]
    1088:	f103011f 	cmp	x8, #0xc0
    108c:	138b1d6d 	ror	w13, w11, #7
    1090:	138c458e 	ror	w14, w12, #17
    1094:	0b0901e9 	add	w9, w15, w9
    1098:	4acb49ad 	eor	w13, w13, w11, ror #18
    109c:	4acc4dce 	eor	w14, w14, w12, ror #19
    10a0:	4a4b0dad 	eor	w13, w13, w11, lsr #3
    10a4:	4a4c29cc 	eor	w12, w14, w12, lsr #10
    10a8:	0b0d0129 	add	w9, w9, w13
    10ac:	0b0c0129 	add	w9, w9, w12
    10b0:	b9004149 	str	w9, [x10, #64]
    10b4:	2a0b03e9 	mov	w9, w11
    10b8:	54fffde1 	b.ne	1074 <Java_org_reference_panel_Guard_probe@@Base+0x640>  // b.any
    10bc:	294036ac 	ldp	w12, w13, [x21]
    10c0:	29412aab 	ldp	w11, w10, [x21, #8]
    10c4:	294326a8 	ldp	w8, w9, [x21, #24]
    10c8:	29423eb0 	ldp	w16, w15, [x21, #16]
    10cc:	aa1f03ee 	mov	x14, xzr
    10d0:	2a0c03e5 	mov	w5, w12
    10d4:	2a0d03e2 	mov	w2, w13
    10d8:	2a0b03e3 	mov	w3, w11
    10dc:	2a0a03e0 	mov	w0, w10
    10e0:	2a0903e6 	mov	w6, w9
    10e4:	2a0803fc 	mov	w28, w8
    10e8:	2a0f03fe 	mov	w30, w15
    10ec:	2a1003e7 	mov	w7, w16
    10f0:	2a1c03e4 	mov	w4, w28
    10f4:	2a0003fc 	mov	w28, w0
    10f8:	2a0303e0 	mov	w0, w3
    10fc:	2a0203e3 	mov	w3, w2
    1100:	2a0503e2 	mov	w2, w5
    1104:	2a0703e1 	mov	w1, w7
    1108:	138718e7 	ror	w7, w7, #6
    110c:	13820857 	ror	w23, w2, #2
    1110:	0a1e0025 	and	w5, w1, w30
    1114:	2a1e03f1 	mov	w17, w30
    1118:	b86e6b7e 	ldr	w30, [x27, x14]
    111c:	0a210099 	bic	w25, w4, w1
    1120:	0b0600a5 	add	w5, w5, w6
    1124:	4ac12ce6 	eor	w6, w7, w1, ror #11
    1128:	4ac236f7 	eor	w23, w23, w2, ror #13
    112c:	b86e6b47 	ldr	w7, [x26, x14]
    1130:	0b1900a5 	add	w5, w5, w25
    1134:	4a030019 	eor	w25, w0, w3
    1138:	0b1e00a5 	add	w5, w5, w30
    113c:	4ac164c6 	eor	w6, w6, w1, ror #25
    1140:	0a03001e 	and	w30, w0, w3
    1144:	0a020339 	and	w25, w25, w2
    1148:	4ac25af7 	eor	w23, w23, w2, ror #22
    114c:	0b0700a5 	add	w5, w5, w7
    1150:	4a1e0327 	eor	w7, w25, w30
    1154:	0b0600a5 	add	w5, w5, w6
    1158:	910011ce 	add	x14, x14, #0x4
    115c:	0b1700e6 	add	w6, w7, w23
    1160:	0b1c00a7 	add	w7, w5, w28
    1164:	f10401df 	cmp	x14, #0x100
    1168:	0b0500c5 	add	w5, w6, w5
    116c:	2a0403e6 	mov	w6, w4
    1170:	2a1103fc 	mov	w28, w17
    1174:	2a0103fe 	mov	w30, w1
    1178:	54fffbc1 	b.ne	10f0 <Java_org_reference_panel_Guard_probe@@Base+0x6bc>  // b.any
    117c:	0b0b006b 	add	w11, w3, w11
    1180:	0b0a000a 	add	w10, w0, w10
    1184:	aa1f03ee 	mov	x14, xzr
    1188:	0b0c00ac 	add	w12, w5, w12
    118c:	0b0d004d 	add	w13, w2, w13
    1190:	29012aab 	stp	w11, w10, [x21, #8]
    1194:	0b1000ea 	add	w10, w7, w16
    1198:	0b0f002b 	add	w11, w1, w15
    119c:	0b080228 	add	w8, w17, w8
    11a0:	0b090089 	add	w9, w4, w9
    11a4:	52800817 	mov	w23, #0x40                  	// #64
    11a8:	290036ac 	stp	w12, w13, [x21]
    11ac:	29022eaa 	stp	w10, w11, [x21, #16]
    11b0:	290326a8 	stp	w8, w9, [x21, #24]
    11b4:	f90036bf 	str	xzr, [x21, #104]
    11b8:	cb160273 	sub	x19, x19, x22
    11bc:	8b160294 	add	x20, x20, x22
    11c0:	b5ffee93 	cbnz	x19, f90 <Java_org_reference_panel_Guard_probe@@Base+0x55c>
    11c4:	f94007e8 	ldr	x8, [sp, #8]
    11c8:	f9401508 	ldr	x8, [x8, #40]
    11cc:	f85f03a9 	ldur	x9, [x29, #-16]
    11d0:	eb09011f 	cmp	x8, x9
    11d4:	54000121 	b.ne	11f8 <Java_org_reference_panel_Guard_probe@@Base+0x7c4>  // b.any
    11d8:	a9574ff4 	ldp	x20, x19, [sp, #368]
    11dc:	a95657f6 	ldp	x22, x21, [sp, #352]
    11e0:	a9555ff8 	ldp	x24, x23, [sp, #336]
    11e4:	a95467fa 	ldp	x26, x25, [sp, #320]
    11e8:	a9536ffc 	ldp	x28, x27, [sp, #304]
    11ec:	a9527bfd 	ldp	x29, x30, [sp, #288]
    11f0:	910603ff 	add	sp, sp, #0x180
    11f4:	d65f03c0 	ret
    11f8:	94000016 	bl	1250 <__stack_chk_fail@plt>

Disassembly of section .plt:

0000000000001200 <__cxa_finalize@plt-0x20>:
    1200:	a9bf7bf0 	stp	x16, x30, [sp, #-16]!
    1204:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1208:	f9421e11 	ldr	x17, [x16, #1080]
    120c:	9110e210 	add	x16, x16, #0x438
    1210:	d61f0220 	br	x17
    1214:	d503201f 	nop
    1218:	d503201f 	nop
    121c:	d503201f 	nop

0000000000001220 <__cxa_finalize@plt>:
    1220:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1224:	f9422211 	ldr	x17, [x16, #1088]
    1228:	91110210 	add	x16, x16, #0x440
    122c:	d61f0220 	br	x17

0000000000001230 <__cxa_atexit@plt>:
    1230:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1234:	f9422611 	ldr	x17, [x16, #1096]
    1238:	91112210 	add	x16, x16, #0x448
    123c:	d61f0220 	br	x17

0000000000001240 <__register_atfork@plt>:
    1240:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1244:	f9422a11 	ldr	x17, [x16, #1104]
    1248:	91114210 	add	x16, x16, #0x450
    124c:	d61f0220 	br	x17

0000000000001250 <__stack_chk_fail@plt>:
    1250:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1254:	f9422e11 	ldr	x17, [x16, #1112]
    1258:	91116210 	add	x16, x16, #0x458
    125c:	d61f0220 	br	x17

0000000000001260 <memcpy@plt>:
    1260:	90000030 	adrp	x16, 5000 <memcpy@plt+0x3da0>
    1264:	f9423211 	ldr	x17, [x16, #1120]
    1268:	91118210 	add	x16, x16, #0x460
    126c:	d61f0220 	br	x17
