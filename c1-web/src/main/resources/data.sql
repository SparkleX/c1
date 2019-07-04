create sequence OCRD_S;
create sequence ORDR_S;
create sequence RDR1_S;
create sequence OITM_S;
create sequence CRD1_S;

insert into OCRD(id,bpCode,bpName) values(OCRD_S.nextval,N'C001',N'C001');
insert into OCRD(id,bpCode,bpName) values(OCRD_S.nextval,N'C002',N'C0022');
insert into CRD1(id,parentId,type,name,address) values(CRD1_S.nextval,1,'B','default','shanghai');
insert into CRD1(id,parentId,type,name,address) values(CRD1_S.nextval,1,'S',null,'shanghai');


insert into OITM(id,itemCode,itemName) values(OITM_S.nextval,N'I001',N'I001');
insert into OITM(id,itemCode,itemName) values(OITM_S.nextval,N'I002',N'I002');
insert into OITM(id,itemCode,itemName) values(OITM_S.nextval,N'I003',N'I003');
insert into ORDR(id,bpId,remarks,docTotal, docStatus) values(ORDR_S.nextval,1,N'desc1', 100.99, 'O');
insert into ORDR(id,bpId,remarks,docTotal, docStatus) values(ORDR_S.nextval,1,N'desc2', 69, 'C');
insert into RDR1(id,parentId,itemId,price,quantity,lineTotal) values(RDR1_S.nextval,1,1,1.1,1.2,1.3);
insert into RDR1(id,parentId,itemId,price,quantity,lineTotal) values(RDR1_S.nextval,1,2,2.1,2.2,2.3);
insert into RDR1(id,parentId,itemId,price,quantity,lineTotal) values(RDR1_S.nextval,1,1,3.1,3.2,3.3);
insert into RDR1(id,parentId,itemId,price,quantity,lineTotal) values(RDR1_S.nextval,2,1,4.1,4.2,4.3);
insert into OADM(id,compnyName,sumDec,qtyDec,priceDec,rateDec,percentDec,measureDec) values(1,'US01',1,2,3,4,5,6);
