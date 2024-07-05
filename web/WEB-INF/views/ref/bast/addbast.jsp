<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>    <li>

        <a href="${pageContext.request.contextPath}/beranda">Home </a>
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/bast"  >Daftar  Berita Acara Serah Terima (BAST)</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Tambah  Berita Acara Serah Terima<span id='statusaddedit'></span></a></li>
</ul>
<div class="portlet box red">

    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Form Tambah  Berita Acara Serah Terima (BAST)</div>
    </div>
    <div class="portlet-body flip-scroll">

        <form:form  method="post" commandName="progcmd" onsubmit="return cekspd()" id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bast/simpanbast" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="idBast" id='idBast'     />
                        <form:hidden path="kontrak.idKontrak" id="idKontrak" value="${kontrak.idKontrak}"  cssClass="required" size="10" maxlength="180" readonly='true'  />

                        <form:input path="tahunAnggaran" id="tahunAnggaran" cssClass="required" type="text" readonly="true" size="4" maxlength="6" value="${tahunAnggaran}"  />
                        <form:errors path="tahunAnggaran" class="tahunAnggaran" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-5">
                    <div class="input-group">

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' value="${skpd.idSkpd}"   />
                        <form:input type="text"  path="skpd.namaSkpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" id="skpd"  class="m-wrap large" size="55" readOnly="true" />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Kegiatan:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" name="banyakakun" id="banyakakun" />

                        <!-- <input type="hidden" name="idspd" id="idspd" /> <form:hidden path="idSpd" id='idSpd'     /> -->
                        <form:hidden path="idkegiatan" id='idkegiatan' readonly='true' value="${idkegiatan}" onchange="getKodeUMK()"  />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  value="${kegiatan.namaKegiatan}" cssClass="required"   type="text" size="80" readonly='true'  />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bast/listpopupkontrak/?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a>

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="bastSebelum" id='bastSebelum' readonly='true' />
                        <form:input path="nilaiKontrak" id="nilaiKontrak"  cssClass="required"   type="text" size="30" readonly='true'  />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Sisa BAST:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:input path="sisaBast" id="sisaBast"  cssClass="required"   type="text" size="30" readonly='true'  />

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor BAST:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noBast" id="noBast"  readonly="true"    size="10" maxlength="50"  />
                        <form:errors path="noBast" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAST :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglBast" path="tglBast" id="tglBast" class="required form-control form-control-inline input-small date-picker entrytanggal2 valid" size="14"  />
                        <form:errors path="tglBast" class="error" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"  cssClass="required"    size="55" maxlength="50"   />
                        <form:errors path="namaPptk" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="20" maxlength="18"   />
                        <form:errors path="nipPptk" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPemeriksaBarang" id="namaPemeriksaBarang"  cssClass="required"   size="55" maxlength="50"   />
                        <form:errors path="namaPemeriksaBarang" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPemeriksaBarang" id="nipPemeriksaBarang"  cssClass="required"   size="20" maxlength="18"   />
                        <form:errors path="nipPemeriksaBarang" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group" id="labelUMK">
                <label class="col-md-3 control-label">Uang Muka :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:checkbox path="statusUangMuka" id="statusUangMuka" onclick="ceknilaientry()" value="1" /> &nbsp; &nbsp; *Transaksi sebagai UMK, Bukan Potongan UMK
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketBast" id="ketBast"  cssClass="required"  size="55" maxlength="250"   />
                        <form:errors path="ketBast" class="error" />
                    </div>
                </div>
            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"    type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bast" >Kembali</a>
                    <!-- <button type="button" class="btn blue" onclick='gridpagukontrak()'>CEK</button> -->
                </div>
            </div>

            <div id="mygrid" class="portlet box">

                <div id="tabelPagu" class="portlet-body">
                    <table id="gridpagu" class="table table-striped table-bordered table-condensed table-hover " >
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Nama Akun</th>
                                <th>Nilai Kontrak</th>
                                <th>Sisa Kontrak</th>
                                <th>Nilai UMK</th>
                                <th>Sisa UMK </th>
                            </tr>
                        </thead>

                        <tbody id="gridpagutablebody" >
                            <tr>

                            </tr>
                        </tbody>


                        <tbody  >
                        </tbody>
                    </table>

                </div>


                <div id="tabelAkun" class="portlet-body">
                    <table id="akunpopup" class="table table-striped table-bordered table-condensed table-hover " >
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>No SPD</th>
                                <th>Nama Akun</th>
                                <th>Nilai SPD</th>
                                <th>Sisa SPD</th>
                                <th>Prestasi</th>
                                <th>Nilai BAST</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tfoot>
                            <tr>
                                <th colspan="6" style="text-align:right">Total:</th>
                                <th>
                                    <input type='text' id="totalbast"  name="totalbast" readonly="true"     style='border:none;margin:0;width:99%; text-align: right;'    />
                                </th>

                            </tr>
                        </tfoot>

                        <tbody  >
                        </tbody>
                    </table>
                </div>

            </div>

        </form:form>
    </div>



</div>





<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/addbast.js"></script>