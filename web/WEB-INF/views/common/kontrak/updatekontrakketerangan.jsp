<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    $(document).ready(function() {
        //validasisisakontrakupdate();
        $("#tglkontrak").val($("#tanggalKontrak").val());

        if ($("#kodeVA").val() == "1") {
            document.getElementById('cbVA').checked = true;
        }

        if ($("#kodeSuratKet").val() == "1") {
            document.getElementById('cbSkp').checked = true;
        }

        setLabelVA();
        getDataNPWPEdit();

    });

    function ambilketerangan() {
        window.localStorage.setItem("ketNilaiKontrak", accounting.unformat($('#nilaiSpd').val(), ","));
        window.localStorage.setItem("ketIdSpd", $("#idSpd").val());
        window.localStorage.setItem("ketIdKegiatan", $("#idKegiatan").val());
        window.localStorage.setItem("ketIdKontrak", $("#idKontrak").val());
        window.localStorage.setItem("ketNamaKegiatan", $("#namaKegiatan").val());

    }

    function getDataNPWPEdit() {
        var varurl = getbasepath() + "/kontrak/json/inquirynpwp";
        var dataac = [];
        var status;

        if ($("#kodePkp").val() == "1") {
            status = "PKP AKTIF";
        } else {
            status = "NON PKP";
        }

        var datajour = {
            npwp: $('#idRekananNpwp').val()
        };
        dataac = datajour;
//
        $.ajax({
            url: varurl,
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(dataac),
            contentType: 'text/plain; charset=utf-8',
            headers: {
                'Accept': 'application/json, text/javascript',
                'Content-Type': 'application/json',
            },
            success: function(result) {

                if (result['error'] == null) {
                    $("#namaNpwp").val(result['NAMA']);
                    $("#alamatNpwp").val(result['ALAMAT']);
                    $("#kotaNpwp").val(result['KABKOT']);
                    $("#kodePkp").val(result['STATUS_PKP']);

                    if (status !== result['STATUS_PKP']) {
                        bootbox.alert("Data Status PKP Telah Berubah, Klik Simpan Untuk Mengubah Data Status PKP");
                    }

                } else {
                    bootbox.alert(result['error']);
                    $("#kodePkp").val(status);
                }
            },
            error: function() {
                bootbox.alert("Sambungan DJP Terputus");
                console.log("status = " + status);
                console.log(" kodePkp = " + $("#kodePkp").val());

                $("#kodePkp").val(status);
            }
        });
    }

</script>

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
    <li><a href="#">Edit Kontrak <span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Edit Kontrak</div>
    </div>
    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"  onsubmit="return cek()" action="${pageContext.request.contextPath}/kontrak/updatekontraks" class="form-horizontal">

            <div class="col-md-7">

                <div class="form-group">
                    <label class="col-md-4 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <form:hidden path="idKontrak" id='idKontrak'     />
                        <form:input path="tahunAnggaran" id="tahunAnggaran" cssClass="required" type="text" readonly="true" size="4" maxlength="4" value="${tahunAnggaran}"  />
                        <form:errors path="tahunAnggaran" class="tahunAnggaran" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                            <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="40" readOnly="true" />
                            <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="skpd.idSkpd" class="label label-important" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kegiatan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="kegiatan.idKegiatan" id='idKegiatan'  />
                            <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  cssClass="required"   type="text" size="55" readonly='true'  />

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Jenis Pajak:</label>
                    <div class="col-md-7">
                        <div class="input-group">

                            <form:select path="kodeBelanja" id="kodeBelanja"  >
                                <form:option value="-">---- Pilih ----</form:option>
                                <form:option value="1">Pajak Pegawai</form:option>
                                <form:option value="2">Pajak Barang/Modal</form:option>
                                <form:option value="3">Pajak Jasa</form:option>

                            </form:select>

                            <form:errors path="kodeBelanja" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Sisa Anggaran :</label>
                    <div class="col-md-7">
                        <input type="text"  name="nilaiSisa"  id="nilaiSisa" readonly="true" />
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">No. Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   cssClass="required"  path="noKontrak" id="noKontrak" readonly="true" size="50" maxlength="50"  />
                            <form:errors path="noKontrak" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Metode:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="metode.kodeMetode" id='kodeMetode'  readonly='true' />
                            &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listmetodepopup?target='_top'" title="Pilih Metode"  ><i class="icon-zoom-in"></i> Pilih</a>
                            <form:input path="metode.ketMetode" id="ketMetode"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true'  />
                            <form:errors path="metode.kodeMetode" class="label label-important" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Dok Referensi:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idDokReferensi" id="idDokReferensi"  cssClass="required"   size="40" maxlength="30" />
                            <form:errors path="idDokReferensi" class="idDokReferensi" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Kontrak Referensi:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idKontrakReferensi" id="idKontrakReferensi"   cssClass="required"     size="40" maxlength="30" />
                            <form:errors path="idKontrakReferensi" class="idKontrakReferensi" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tanggal Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="tanggalKontrak" id='tanggalKontrak'  />
                            <input readonly="true" type="text" name="tglkontrak" id="tglkontrak"  size="14" value=""/>
                            <form:errors path="tanggalKontrak" class="error" />
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label">Nilai Kontrak Tahun Ini:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <input type="hidden" id="nilaiKontrakOrg" name="nilaiKontrakOrg"  value="${progcmd.nilaiSpdOrig}"/>
                            <form:input path="nilaiSpd" id="nilaiSpd" value="${nilaiSpd}" cssClass="required  ruleCeknilaiKontrak ceknilaikontrakvsbast "   size="30" maxlength="17" readonly="true" />
                            <form:errors path="nilaiSpd" class="error" />
                        </div>
                    </div>
                </div>

                <div id="labelmultiyear" class="form-group">
                    <label class="col-md-4 control-label">Total Nilai Kontrak (Multiyear):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="nilaiKontrakTotal" id="nilaiKontrakTotal"  size="30" maxlength="17" cssClass="required ruleCeknilaiKontrakMultiyear" />
                            <input type="hidden" id="kodeMultiyear" name="kodeMultiyear"  value=""/>
                        </div>
                    </div>
                </div>

                <div id="labelnomultiyear" name="labelnomultiyear" class="form-group">
                    <label class="col-md-4 control-label">No Kontrak (Multiyear):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="noKontrakTotal" id="noKontrakTotal"  size="50" maxlength="50" cssClass="required" />
                            <form:errors path="noKontrakTotal" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Dok TTKontrNo:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idDokKontrNo" id="idDokKontrNo"  cssClass="required"   size="40" maxlength="30" />
                            <form:errors path="idDokKontrNo" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No noper Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idKontrakMohon" id="idKontrakMohon"  cssClass="required"   size="30" maxlength="30" />
                            <form:errors path="idKontrakMohon" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl noper Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tglKontrakMohon" path="tglKontrakMohon" id="tglKontrakMohon" cssClass="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" />
                            <form:errors path="tglKontrakMohon" class="error" />

                        </div>
                    </div>
                </div>
                <form:hidden   path="kodeAnggaranSumbdana" id="kodeAnggaranSumbdana" value="1"   />

                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl Dok TTKontr:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tanggalDokTtkontr" path="tanggalDokTtkontr" id="tanggalDokTtkontr" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="tanggalDokTtkontr" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"> Dok Simpan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idDokSimpan" id="idDokSimpan"  size="40" maxlength="30" />
                            <form:errors path="idDokSimpan" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Lokasi Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idLokasiKontrak" id="idLokasiKontrak" cssClass="required"  maxlength="30"   size="40" />
                            <form:errors path="idLokasiKontrak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kode Wilayah SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">

                            <form:select cssClass="required" path="kodeWilayahSkpd" id="kodeWilayahSkpd" >
                                <form:option value="0">Provinsi </form:option>
                                <form:option value="1">Jakarta Pusat </form:option>
                                <form:option value="2">Jakarta Utara </form:option>
                                <form:option value="3">Jakarta Barat </form:option>
                                <form:option value="4">Jakarta Selatan     </form:option>
                                <form:option value="5">Jakarta Timur     </form:option>
                                <form:option value="6">Kepulauan Seribu     </form:option>
                            </form:select>
                            <form:errors path="kodeWilayahSkpd" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kode Wilayah Proses SPM:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   cssClass="required" path="kodeWilayahProsesSpm" id="kodeWilayahProsesSpm" maxlength="1"    size="30" />
                            <form:errors path="kodeWilayahProsesSpm" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Ket PEKR Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="ketPekrKontrak" id="ketPekrKontrak"    size="80" maxlength="250" />
                            <form:errors path="ketPekrKontrak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label"> SKPD/Pimpro:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idSkPimPro" id="idSkPimPro"  maxlength="30"  size="40" />
                            <form:errors path="idSkPimPro" class="idSkPimPro" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kepala SKPD/Pimpro:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaPimPro" id="namaPimPro"    size="40" maxlength="30" />
                            <form:errors path="namaPimPro" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl SK Pimpro:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tanggalSkPimPro" path="tanggalSkPimPro" id="tanggalSkPimPro" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="tanggalSkPimPro" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Perusahaan Tender:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idRekananTender" id="idRekananTender"  maxlength="30"  size="40" />
                            <form:errors path="idRekananTender" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Perusahaan DIKDIP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idRekananDikdip" id="idRekananDikdip"    size="40" maxlength="30" />
                            <form:errors path="idRekananDikdip" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl Perusahaan DIKDIP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tanggalRekananDikdip" path="tanggalRekananDikdip" id="tanggalRekananDikdip" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="tanggalRekananDikdip" class="error" />

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">No PO:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   cssClass="required" path="nomorPo" id="nomorPo"   maxlength="30" size="20" />
                            <form:errors path="nomorPo" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl Awal Pelaksanaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tanggalAwalJadwalKontrak" path="tanggalAwalJadwalKontrak" id="tanggalAwalJadwalKontrak" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="tanggalAwalJadwalKontrak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tgl Akhir Pelaksanaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="tanggalAkhirJadwalKontrak" path="tanggalAkhirJadwalKontrak" id="tanggalAkhirJadwalKontrak" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="tanggalAkhirJadwalKontrak" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Denda Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idDendaKontrak" id="idDendaKontrak"  maxlength="30"  size="20" />
                            <form:errors path="idDendaKontrak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Bukti Denda Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idBuktiDendaKontrak" id="idBuktiDendaKontrak"   maxlength="30" size="20" />
                            <form:errors path="idBuktiDendaKontrak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Kontrak Pph:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idKontrakPph" id="idKontrakPph"  maxlength="20"  size="25" />
                            <form:errors path="idKontrakPph" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Lelang:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idLelang" id="idLelang"   maxlength="30" size="20" />
                            <form:errors path="idLelang" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Revisi:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="idRevisi" id="idRevisi" maxlength="30"   size="20" />
                            <form:errors path="idRevisi" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No NPWP Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!-- ruleCekNpwpRekanan -->
                            <form:input  path="rekanan.idRekananNpwp" id="idRekananNpwp" size="40" maxlength="15" onkeypress="return isNumber(event)" onchange="getDataNPWP()"/>
                            <form:errors path="rekanan.idRekananNpwp" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama NPWP Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="namaNpwp" id="namaNpwp"  cssClass="required "  size="40" maxlength="100" readonly="true" />
                            <form:errors path="namaNpwp" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat NPWP Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="alamatNpwp" id="alamatNpwp"  cssClass="required "  size="75"    maxlength="100" readonly="true" />
                            <form:errors path="alamatNpwp" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kota NPWP Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="kotaNpwp" id="kotaNpwp"  cssClass="required "  size="40"    maxlength="50" readonly="true" />
                            <form:errors path="kotaNpwp" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Status PKP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="kodePkp" id="kodePkp"  cssClass="required "  size="40"    maxlength="50" readonly="true" />
                            <form:errors path="kodePkp" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Surat Keterangan Pajak :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <input type="checkbox" id="cbSkp" id="cbSkp" onchange="setSKP()" />
                            <form:hidden path="kodeSuratKet" id='kodeSuratKet'  />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">No Surat Keterangan Pajak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="nomorSuratKet" id="nomorSuratKet"  cssClass="required "  size="40"  />
                            <form:errors path="nomorSuratKet" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">No Akte Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  cssClass="required" path="rekanan.idRekananAkte" id="idRekananAktec"  value=''  size="40"    maxlength="30" />
                            <form:errors path="rekanan.idRekananAkte" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tanggal Akte Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" name="rekanan.tanggalRekananAkte" path="rekanan.tanggalRekananAkte" id="tanggalRekananAktec" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14" value=""/>
                            <form:errors path="rekanan.tanggalRekananAkte" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Perusahaan (Tujuan):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="rekanan.namaRekanan" id="rekanan"    size="40" maxlength="50" cssClass="ruleCekNamaRekanan" />
                            <form:errors path="rekanan.namaRekanan" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Perusahaan (Bank):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="namaRekananBank" id="namaRekananBank"    size="40" maxlength="50" cssClass="required ruleCekGeneralError"  />
                            <form:errors path="namaRekananBank" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="rekanan.alamatRekanan" id="alamatRekanan"    size="75"   maxlength="250" />
                            <form:errors path="rekanan.alamatRekanan" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Direktur:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="rekanan.namaDirekturRekanan" id="namaDirekturRekanan"    size="40"   maxlength="30" />
                            <form:errors path="rekanan.namaDirekturRekanan" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bank Utama:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="idBankPfk" id='idBankPfk' />
                            <form:hidden path="kodeBankPfk" id='kodeBankPfk' />
                            <form:hidden path="kodeBankTransfer" id='kodeBankTransfer' onchange="setLabelVA()"/>
                            <form:hidden path="namaBankPfk" id='namaBankPfk' />
                            <form:input path="namaBankTransfer" id="namaBankTransfer" size="65" cssClass="required" type="text" onchange="getDataBankDki()" readonly="true"/> &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listbankinduk?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bank Cabang:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="namaBank" id="namaBank"    size="40"   maxlength="75" />
                            <form:errors path="namaBank" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat Bank Cabang:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="alamatBank" id="alamatBank"    size="40"   maxlength="250" />
                            <form:errors path="alamatBank" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Rekening Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="rekeningBank" id="rekeningBank"    size="40"   maxlength="30" onKeyPress="return numbersonly(this, event)" onchange="getDataBankDki()"/>
                            <form:errors path="rekeningBank" class="error" />
                        </div>
                    </div>
                </div>

                <div id="labelva" class="form-group">
                    <label class="col-md-4 control-label">Virtual Account :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <input type="checkbox" id="cbVA" id="cbVA" onchange="setkodeVA()" />
                            <form:hidden path="kodeVA" id='kodeVA'  />
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-4 col-md-offset-1">
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>

                <div class="portlet box red ">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Bank DKI</div>

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkikodebank" id="dkikodebank" type="text" readonly="true" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">No Rekening :</label>
                                <div class="col-md-5">
                                    <input name="dkinorek" id="dkinorek" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkinama" id="dkinama" cols="30" ROWS="3" readonly="true"></TEXTAREA>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Alamat :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkialamat" id="dkialamat" cols="30" ROWS="3" readonly="true"></TEXTAREA>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"  type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="#" onclick="pindahhal()">Kembali</a>
                    <a id="btnKontrakRinci" class="fancybox fancybox.iframe btn blue" onclick="ambilketerangan()" href="${pageContext.request.contextPath}/kontrakrinci/addkontrakrinci?target='_top'" title="Kontrak Rinci"  ></i>Input Kontrak Rinci</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/addkontrak.js"></script>