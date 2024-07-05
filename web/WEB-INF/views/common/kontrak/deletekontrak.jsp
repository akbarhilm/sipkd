<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhal()" >Daftar Kontrak</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Hapus Kontrak <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Hapus Kontrak</div>       
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/kontrak/deletekontraks" class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="idKontrak" id='idKontrak'     />                  
                    <form:input readonly="true" path="tahunAnggaran" id="tahunAnggaran" cssClass="required" type="text"  size="4" maxlength="6" value="${tahunAnggaran}"  /> 
                    <form:errors path="tahunAnggaran" class="tahunAnggaran" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                        <form:input readonly="true" type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="40" readOnly="true" />
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="kegiatan.idKegiatan" id='idKegiatan'   onChange="getpagudanspd(this.value)"  />
                        <form:input readonly="true" path="kegiatan.namaKegiatan" id="namaKegiatan"  cssClass="required"   type="text" size="55" maxlength="180" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="spd.idSpd" id="idSpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                        <form:errors path="spd.idSpd" class="label label-important" />
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label class="col-md-3 control-label">No. Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="noKontrak" id="noKontrak"    size="30" />  
                        <form:errors path="noKontrak" class="error" />
                    </div>
                </div>  
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Metode:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="metode.kodeMetode" id='kodeMetode'  readonly='true' />
                        <form:errors path="metode.kodeMetode" class="label label-important" />
                    </div>
                </div>
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">No Dok Referensi:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idDokReferensi" id="idDokReferensi"  cssClass="required"   size="40" />  
                        <form:errors path="idDokReferensi" class="idDokReferensi" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No Kontrak Referensi:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idKontrakReferensi" id="idKontrakReferensi"   cssClass="required"     size="40" />  
                        <form:errors path="idKontrakReferensi" class="idKontrakReferensi" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:input readonly="true" type="text" name="tanggalKontrak" path="tanggalKontrak" id="tanggalKontrak" class="required" size="14" value=""/>
                        <form:errors path="tanggalKontrak" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="nilaiSpd" id="nilaiSpd" class="inputinvoice"   size="30" />  
                        <form:errors path="nilaiSpd" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Dok TTKontrNo:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idDokKontrNo" id="idDokKontrNo"  cssClass="required"   size="40" />  
                        <form:errors path="idDokKontrNo" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No noper Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idKontrakMohon" id="idKontrakMohon"  cssClass="required"   size="30" />  
                        <form:errors path="idKontrakMohon" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl noper Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tglKontrakMohon" path="tglKontrakMohon" id="tglKontrakMohon" class="required" size="14" value=""/>
                        <form:errors path="tglKontrakMohon" class="error" />

                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Anggaran Sumbdana:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="kodeAnggaranSumbdana" id="kodeAnggaranSumbdana"    size="10" maxlength="1" />  
                        <form:errors path="kodeAnggaranSumbdana" class="error" />
                    </div>
                </div>  
            </div>



            <div class="form-group">
                <label class="col-md-3 control-label">Tgl Dok TTKontr:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tanggalDokTtkontr" path="tanggalDokTtkontr" id="tanggalDokTtkontr" class="required" size="14" value=""/>
                        <form:errors path="tanggalDokTtkontr" class="error" />

                    </div>
                </div>  
            </div>       
            <div class="form-group">
                <label class="col-md-3 control-label">Id Dok Simpan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idDokSimpan" id="idDokSimpan"  maxlength="15"  size="40" />  
                        <form:errors path="idDokSimpan" class="error" />
                    </div>
                </div>  
            </div>      
            <div class="form-group">
                <label class="col-md-3 control-label">Id Lokasi Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idLokasiKontrak" id="idLokasiKontrak" cssClass="required"  maxLength="25"  size="40" />  
                        <form:errors path="idLokasiKontrak" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Wilayah SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="kodeWilayahSkpd" id="kodeWilayahSkpd"   maxLength="1" size="30" />  
                        <form:errors path="kodeWilayahSkpd" class="error" />
                    </div>
                </div>  
            </div>    
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Wilayah Proses SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="kodeWilayahProsesSpm" id="kodeWilayahProsesSpm" maxlength="1"    size="30" />  
                        <form:errors path="kodeWilayahProsesSpm" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Ket PEKR Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="ketPekrKontrak" id="ketPekrKontrak"    size="80" />  
                        <form:errors path="ketPekrKontrak" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Id SKPD/Pimpro:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idSkPimPro" id="idSkPimPro"  maxlength="25"  size="40" />  
                        <form:errors path="idSkPimPro" class="idSkPimPro" />
                    </div>
                </div>  
            </div>        
            <div class="form-group">
                <label class="col-md-3 control-label">Kepala SKPD/Pimpro:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="namaPimPro" id="namaPimPro"    size="40" />  
                        <form:errors path="namaPimPro" class="error" />
                    </div>
                </div>    
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl SK Pimpro:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tanggalSkPimPro" path="tanggalSkPimPro" id="tanggalSkPimPro" class="required" size="14" value=""/>
                        <form:errors path="tanggalSkPimPro" class="error" />

                    </div>
                </div>  
            </div>    
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekanan Tender:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idRekananTender" id="idRekananTender"  maxlength="15"  size="40" />  
                        <form:errors path="idRekananTender" class="error" />
                    </div>
                </div>  
            </div>    
            <div class="form-group">
                <label class="col-md-3 control-label">No Rekanan DIKDIP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idRekananDikdip" id="idRekananDikdip"    size="40" />  
                        <form:errors path="idRekananDikdip" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl Rekanan DIKDIP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tanggalRekananDikdip" path="tanggalRekananDikdip" id="tanggalRekananDikdip" class="required" size="14" value=""/>
                        <form:errors path="tanggalRekananDikdip" class="error" />

                    </div>
                </div>  
            </div>    

            <div class="form-group">
                <label class="col-md-3 control-label">No PO:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="nomorPo" id="nomorPo"   maxlength="15" size="20" />  
                        <form:errors path="nomorPo" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl Awal Pelaksanaan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tanggalAwalJadwalKontrak" path="tanggalAwalJadwalKontrak" id="tanggalAwalJadwalKontrak" class="required" size="14" value=""/>
                        <form:errors path="tanggalAwalJadwalKontrak" class="error" />

                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl Akhir Pelaksanaan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" type="text" name="tanggalAkhirJadwalKontrak" path="tanggalAkhirJadwalKontrak" id="tanggalAkhirJadwalKontrak" class="required" size="14" value=""/>
                        <form:errors path="tanggalAkhirJadwalKontrak" class="error" />

                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No Denda Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idDendaKontrak" id="idDendaKontrak"  maxlength="15"  size="20" />  
                        <form:errors path="idDendaKontrak" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">No Bukti Denda Kontrak:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idBuktiDendaKontrak" id="idBuktiDendaKontrak"   maxlength="15" size="20" />  
                        <form:errors path="idBuktiDendaKontrak" class="error" />
                    </div>
                </div>  
            </div>       
            <div class="form-group">
                <label class="col-md-3 control-label">No Kontrak Pph:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idKontrakPph" id="idKontrakPph"  maxlength="20"  size="25" />  
                        <form:errors path="idKontrakPph" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">No Lelang:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idLelang" id="idLelang"   maxlength="15" size="20" />  
                        <form:errors path="idLelang" class="error" />
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">No Revisi:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="idRevisi" id="idRevisi" maxlength="15"   size="20" />  
                        <form:errors path="idRevisi" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">No NPWP Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly='true'   path="rekanan.idRekananNpwp" id="idRekananNpwp"    size="40"    />  
                        <form:errors path="rekanan.idRekananNpwp" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No Akte Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" path="rekanan.idRekananAkte" id="idRekananAktec"  value=''  size="40"    />  
                        <form:errors path="rekanan.idRekananAkte" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal Akte Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"    path="rekanan.tanggalRekananAkte" id="tanggalRekananAktec"    size="40"    />  
                        <form:errors path="rekanan.tanggalRekananAkte" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" path="rekanan.namaRekanan" id="rekanan"    size="40" />  
                        <form:errors path="rekanan.namaRekanan" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Rekanan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"   path="rekanan.alamatRekanan" id="alamatRekanan"    size="75"   />  
                        <form:errors path="rekanan.alamatRekanan" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Direktur:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true"  path="rekanan.namaDirekturRekanan" id="namaDirekturRekanan"    size="40"   />  
                        <form:errors path="rekanan.namaDirekturRekanan" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" path="namaBank" id="namaBank"    size="40"   />  
                        <form:errors path="namaBank" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Alamat Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" path="alamatBank" id="alamatBank"    size="40"   />  
                        <form:errors path="alamatBank" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">Rekening Bank:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input readonly="true" path="rekeningBank" id="rekeningBank"    size="40"   />  
                        <form:errors path="rekeningBank" class="error" />
                    </div>
                </div>  
            </div> 


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Delete </button>
                    <a class="btn blue"  href="#" onclick="pindahhal()">Kembali</a>
                </div>
            </div>
        </form:form>
    </div>
</div> 

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/addkontrak.js"></script>  