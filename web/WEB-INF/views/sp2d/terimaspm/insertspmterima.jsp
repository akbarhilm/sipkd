<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    $(document).ready(function() {
        getDataBankDki();

        if ($('#kodeVA').val() == "1") {
            document.getElementById("cbVA").checked = true;
        }

    });




</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spmterima">Daftar SPM Terima</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah Data SPM Terima <span id='statusaddedit'></span></a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah data SPM Terima</div>
    </div>
    <div class="portlet-body flip-scroll">
        <div class="col-md-7">
            <form:form  method="post" commandName="progcmd1"  id="progcmd1"  onsubmit="return cek()"  action="${pageContext.request.contextPath}/spmterima/prosesinsert" class="form-horizontal"   >
                <div class="form-group" id="labelidSpmCetak" >
                    <label class="col-md-3 control-label">ID SPM Cetak :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input name="idSpmCetak"  path="idSpmCetak" id="idSpmCetak" size="25" maxlength="12" readonly="true"  />  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:hidden name="tahun"  path="tahun" id="tahun" value="${tahunAnggaran}" size="5" maxlength="5"  />
                            <form:errors path="idSpmCetak" class="error" />

                            <label class="control-label">Kode Bank : </label>&nbsp &nbsp &nbsp &nbsp

                            <form:input   path="kodeBank" id="kodeBank" size="4" maxlength="3" readonly="true" cssClass="required "/>
                            <form:errors path="kodeBank" class="error" />

                        </div>
                    </div>
                </div>

                <div class="form-group" id="labelwilsp2d" >
                    <label class="col-md-3 control-label">Wilayah SP2D Proses :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input name="codeWilSp2dproses"  path="codeWilSp2dproses" id="codeWilSp2dproses" size="25" maxlength="1" readonly="true"  />   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="codeWilSp2dproses" class="error" />

                            <label class="control-label">Nama Bank : </label>&nbsp &nbsp &nbsp

                            <form:input   path="namaBank" id="namaBank" size="25" maxlength="40" readonly="true" />
                            <form:errors path="namaBank" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group" id="labelNoSPM">
                    <label class="col-md-3 control-label">No SPM :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input name="iSpmno"  path="iSpmno" id="iSpmno" size="25" maxlength="12" readonly="true" />   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="iSpmno" class="error" />
                            <form:hidden path="kodeVA" id="kodeVA" />

                            <label class="control-label">No Rekening : </label>&nbsp &nbsp;

                            <form:input   path="noRekening" id="noRekening" size="25" maxlength="" readonly="true"/>
                            <form:errors path="noRekening" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">No Dokumen SPM  :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="iSpmnoDok" id="iSpmnoDok" size="29" style="font-size: 12px" maxlength="40" readonly="true" onchange="setButtonakun()"  />   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="iSpmnoDok" class="error" />

                            <label class="control-label">Nama Rekan : </label>&nbsp &nbsp;
                            <!--class="required " ruleCekNamaRekanan -->
                            <form:textarea path="namaRekanan" id="namaRekanan"  cols="24" ROWS="3" readonly="true" />
                            <form:errors path="namaRekanan" class="error" />

                        </div>
                    </div>
                </div>

                <div class="form-group" id="labelSkpd2b" >
                    <label class="col-md-3 control-label">Jenis :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="codeJenis" id="codeJenis" size="25" maxlength="20" readonly="true"   />   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="codeJenis" class="error" />

                            <label class="control-label">NPWP Rekan : </label>&nbsp &nbsp

                            <form:input   path="npwpRekanan" id="npwpRekanan" size="25"  readonly="true" />
                            <form:errors path="npwpRekanan" class="error" />

                        </div>
                    </div>
                </div>
                <div class="form-group" id="labelSkpd2">
                    <label class="col-md-3 control-label">Beban :</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="codeBeban" id="codeBeban" size="25" maxlength="6"  readonly="true"/>  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="codeBeban" class="error" />

                            <label class="control-label">Nama Tujuan : </label>&nbsp &nbsp

                            <!--   path="namaTujuan" id="namaTujuan" size="25"  readonly="true" cssClass="ruleCekNamaTujuan ruleCekNamaRekanan" />  -->
                            <form:textarea path="namaTujuan" id="namaTujuan" class="required " cols="24" ROWS="3" readonly="true" cssClass="ruleCekNamaTujuan"  />

                            <form:errors path="namaTujuan" class="error" />

                        </div>
                    </div>
                </div>

                <div class="form-group" >
                    <label class="col-md-3 control-label">Kode SKPD:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="codeSkpd" id="codeSkpd" size="25" maxlength="30" readonly="true" />   &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <form:errors path="codeSkpd" class="error" />

                            <label class="control-label">Virtual Account : </label>&nbsp &nbsp
                            <input type="checkbox" id="cbVA" id="cbVA" disabled="true"/>

                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-md-3 control-label">Nama SKPD:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="namaSkpd" id="namaSkpd" size="80" readonly="true" />
                            <form:errors path="namaSkpd" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-md-3 control-label">Tgl Cetak SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="dSpmCetak" id="dSpmCetak" size="25" maxlength="12" readonly="true" />
                            <form:errors path="dSpmCetak" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-md-3 control-label">Keterangan SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:textarea   path="eSpm" id="eSpm" cols="48" rows="3"  />
                            <form:errors path="eSpm" class="error" />
                        </div>
                    </div>
                </div>

                <div class="form-group" >
                    <label class="col-md-3 control-label">Petugas Penerima Dokumen SPM:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="namaPegTerimakpkd" id="namaPegTerimakpkd" size="25" maxlength="75" onchange="validasisimpan()"  />
                            <form:errors path="namaPegTerimakpkd" class="error" />
                        </div>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-md-3 control-label">Petugas Pemberi Dokumen SPM dari SKPD: </label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="namaPegPemberiskpd" id="namaPegPemberiskpd" size="25" maxlength="75" onchange="validasisimpan()"  />
                            <form:errors path="namaPegPemberiskpd" class="error" />
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-4 col-md-offset-1">
                <div class="portlet box red">
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
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31"/>

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

                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
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

            <div class="col-md-4 col-md-offset-1">
                <p > &nbsp;</p> <p > &nbsp;</p>

                <div class="portlet box red">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data NPWP Rekanan </div>

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">

                            <div class="form-group">
                    <label class="col-md-4 control-label">NPWP :</label>
                    <div class="col-md-5">
                        <div class="input-group">
                                        <form:input path="noNpwp" id="noNpwp"  onkeypress="return isNumber(event)" readonly="true" type="text" maxlength="15"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama NPWP:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                                        <form:input  path="namaNpwp" id="namaNpwp"  cssClass="required "    maxlength="75" readonly="true" />
                                        <form:errors path="namaNpwp" class="error" />
                                        <input type="hidden" id="namawp"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat NPWP:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                                        <form:textarea path="alamatNpwp" id="alamatNpwp" class="required " maxlength="100"  cols="30" ROWS="3" readonly="true"/>
                                        <form:errors path="alamatNpwp" class="error" />
                                        <input type="hidden" id="alamatwp"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Kota NPWP:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                                        <form:input  path="kotaNpwp" id="kotaNpwp"  cssClass="required "   maxlength="50" readonly="true" />
                                        <form:errors path="kotaNpwp" class="error" />
                                         <input type="hidden" id="kotawp"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Status PKP:</label>
                    <div class="col-md-5">
                        <div class="input-group">
                                        <form:input  path="kodePkp" id="kodePkp"  cssClass="required "   maxlength="50" readonly="true" />
                                        <form:errors path="kodePkp" class="error" />
                            <input type="hidden" id="pkp"/>
                        </div>
                    </div>
                </div>

                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>
        </div>
    </div>
    <div class="form-actions fluid">
        <div class="col-md-offset-3 col-md-9">
            <button id="buttonsimpan"   type="submit" class="btn blue"  onchange="validasisimpan()"  > Simpan </button>
            <a class="btn blue"  href="${pageContext.request.contextPath}/spmterima" >Kembali</a>
            <button type="button" id="buttoncetak" class="btn blue" onclick="cetakjurnal()" href="#">Cetak Tanda Terima</button>
            <button type="button" id="buttonbatal" class="btn blue"  onclick="cekJurnal()" > Batal </button>
        </div>
    </div>
</form:form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/terimaspm/terimaspmlist.js"></script>
