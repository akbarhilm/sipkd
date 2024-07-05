<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<script type="text/javascript">

    $(document).ready(function() {

        if ($("#kodeSuratKet").val() == "1") {
            document.getElementById('cbSkp').checked = true;
        }

        getDataBankDki();
        getDataNPWPEdit();
    });

    function setSKP() {

        if (document.getElementById('cbSkp').checked) {
            $("#kodeSuratKet").val("1");
        } else {
            $("#kodeSuratKet").val("0");
        }

    }

    function getDataNPWPEdit() {
        var varurl = getbasepath() + "/postdata/json/inquirynpwp";
        var dataac = [];
        var status;

        if ($("#kodePkp").val() == "1") {
            status = "PKP AKTIF";
        } else {
            status = "NON PKP";
        }
        console.log("kode Pkp = " + $("#kodePkp").val());
        console.log("status pkp = " + status);

        var datajour = {
            npwp: $('#noNpwp').val()
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

                    console.log("result STATUS_PKP = " + result['STATUS_PKP']);
                    if (status !== result['STATUS_PKP']) {
                        bootbox.alert("Data Status PKP Telah Berubah, Klik Simpan Untuk Mengubah Data Status PKP");
                    }

                } else {
                    $("#kodePkp").val(status);
                    bootbox.alert(result['error']);
                }
            },
            error: function() {
                bootbox.alert("Sambungan DJP Terputus");

                $("#kodePkp").val(status);
            }
        });
    }

</script>

<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPM </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/spmblls/indexspmblls" onclick="">Daftar SPM BL LS</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Edit SPM BL LS (Data Rekan Kontrak)</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form SPM BL LS</div>
    </div>

    <div class="portlet-body flip-scroll">
        <form:form  method="post" commandName="refspmbllspfk"  id="refspmbllspfk" onsubmit="return cekvalidasi()"  action="${pageContext.request.contextPath}/spmblls/prosessimpanpfk" class="form-horizontal">
            <div class="col-md-7">
                <div class="form-group">
                    <label class="col-md-4 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <form:hidden path="id" id='id' value="${idspp}"   />
                        <form:hidden path="idKontrak" id='idKontrak' value="${idKontrak}"   />
                        <form:hidden  path="kodeNihil" id="kodeNihil"  />
                        <form:hidden path="idbas" id='idbas'    />
                        <form:hidden path="nilaiSpp" id='nilaiSpp'    />

                        <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                        <input type="hidden" id="potonganUangMuka" name="potonganUangMuka" />
                        <input type="hidden" id="potongan" name="potongan" />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd' value="${idskpdu}"   onChange="getpagudanspd(this.value)"  />
                            <form:input path="skpd.namaSkpd" id="skpd" value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  cssClass="required"   type="text" size="55"   readonly='true'  />
                            <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                            <form:errors path="skpd.idSkpd" class="label label-important" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No SPP:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No SPM:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <c:if test="${setids == 0}">
                                <form:hidden  path="idspm" value="" />
                            </c:if>
                            <c:if test="${setids != 0}">
                                <form:hidden  path="idspm" />
                            </c:if>
                            <form:input path="noSpm" id="noSpm" type="text" size="25" maxlength="20" readonly='true'  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Tanggal SPM:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input type="text" path="tglSpm" id="tglSpm" class="required valid" readonly="true" size="14" value=""/>
                            <!-- path="tglSpm" id="tglSpm" type="text" size="13" maxlength="14" tabindex="0"  cssStyle="background-color: #F1F1D9"  cssClass="required   entrytanggal "     /> -->

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No.Kontrak:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noKontrak" id="noKontrak" readOnly="true" type="text" size="30" maxlength="50" tabindex="0"  cssStyle="background-color: #F1F1D9"   />
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
                    <label class="col-md-4 control-label">NPWP Rekanan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noNpwp" id="noNpwp"  onkeypress="return isNumber(event)"  onchange="getDataNPWP()" type="text" size="30" maxlength="15" tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama NPWP Perusahaan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input  path="namaNpwp" id="namaNpwp"  cssClass="required "  size="40"    maxlength="100" readonly="true" />
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
                    <label class="col-md-4 control-label">Nama Direktur Rekanan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="direkturRekanan" id="direkturRekanan" type="text" size="30"  tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                    <div class="form-group">
                    </div>
                    <label class="col-md-4 control-label">Nama Perusahaan (Tujuan):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="namaRekanan" id="namaRekanan" type="text" size="30"  tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Perusahaan (Bank):</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="namaRekananBank" id="namaRekananBank" type="text" size="30"  tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat Rekanan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:textarea name="alamatRekanan" path="alamatRekanan"  id="alamatRekanan" cols="48" ROWS="3" maxlength="400" cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="idBankPfk" id='idBankPfk'    />
                            <form:hidden path="kodeBankPfk" id='kodeBankPfk'   />
                            <form:hidden path="kodeBankTransfer" id='kodeBankTransfer'     />
                            <form:hidden path="namaBankPfk" id='namaBankPfk'     />
                            <form:input path="namaBank" id="namaBankTransfer" type="text"  size="50" maxlength="50" tabindex="0"  readonly="true" cssStyle="background-color: #F1F1D9"   />
                            &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/spmblls/listbankinduk?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a>

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">No Rekening Rekanan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="noRekeningPFK" id="noRekeningPFK"  onkeypress="return isNumber(event)"  type="text" size="30" maxlength="30" onchange="getDataBankDki()" tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Alamat Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input path="alamatBank" id="alamatBank" type="text" size="70" maxlength="100" tabindex="0"  cssStyle="background-color: #F1F1D9"   />
                        </div>
                    </div>
                </div>
                <div class="form-group" id="labelVA">
                    <label class="col-md-4 control-label">Virtual Account:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <c:if test="${cek1 != 0}">
                                <form:checkbox path="virtaBank" id="virtaBank" checked="true" onchange="cekVA()" /> Virtual Account
                                <input type="hidden" id="kodeva" name="kodeva" value="1" />
                            </c:if>
                            <c:if test="${cek1 == 0}">
                                <form:checkbox path="virtaBank" id="virtaBank" onchange="cekVA()"/> Bukan Virtual Account
                                <input type="hidden" id="kodeva" name="kodeva" value="0" />
                            </c:if>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-4 col-md-offset-1">
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>
                <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p> <p > &nbsp;</p>

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
                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Alamat :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkialamat" id="dkialamat" cols="30" ROWS="3" readonly="true"></TEXTAREA>

                                </div>
                            </div>
                            <!--
                            <div class="form-group">
                                <label class="col-md-4 control-label">NPWP :</label>
                                <div class="col-md-5">
                                    <input name="dkinpwp" id="dkinpwp" type="text" readonly="true" size="31" />
                                </div>
                            </div>
                            -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onclick="setnilai()" onkeyup="enableddetail()" type="submit" class="btn blue" >Simpan</button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/spmblls/indexspmblls" onclick="" >Kembali</a>

                </div>
    </div>

                    <div class="portlet">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Rincian SPP BL LS</div>
    </div>
    <input type="hidden" id="banyakrinci" name="banyakrinci"  />
    <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
        <thead>
            <tr>
                <th>No</th>
                <th>Nomor Akun</th>
                <th>Nama Akun</th>
                <th>Nilai SPM</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th colspan="3" style="text-align:right">Total:</th>
                <th>
                    <input type='text' id="totalspp"   name="totalspp" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                </th>

            </tr>
        </tfoot>
        <tbody id="spprincitablebody" >
        </tbody>
    </table>
</div>

            <form:hidden path="dokTtd.nama" />
            <form:hidden path="dokTtd.nip" />
        </form:form>
    </div>
</div>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmblls/addspmblls.js"></script>