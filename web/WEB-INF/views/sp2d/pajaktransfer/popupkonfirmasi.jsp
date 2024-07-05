<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form  method="post" commandName="refsp2d"  id="refsp2d"   action="${pageContext.request.contextPath}/sp2dblls/prosessimpan" class="form-horizontal">

    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Konfirmasi Pembayaran Pajak</div>

        </div>

        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Pajak:</label>
                <div class="col-md-4">
                    <input type="hidden" id="isadd" name="isadd"  />
                    <input name="tahunpajak" id="tahunpajak" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <input name="skpd" id="skpd" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No SP2D:</label>
                <div class="col-md-4">
                    <input name="nosp2d" id="nosp2d" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Jenis Pajak:</label>
                <div class="col-md-4">
                    <input name="jenisPajak" id="jenisPajak" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group" id="potpersen">
                <label class="col-md-3 control-label">Potongan Persen:</label>
                <div class="col-md-4">
                    <input name="jenisPajak" id="potPersen" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Masa Pajak :</label>
                <div class="col-md-7">
                    <div class="input-group">
                        <input type="hidden" id="masaPajak" name="masaPajak"  />

                        <select name="masapajak1" id="masapajak1" disabled>
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>

                        </select> &nbsp; sampai &nbsp;
                        <select name="masapajak2" id="masapajak2" disabled>
                            <option value="01">Januari</option>
                            <option value="02">Februari</option>
                            <option value="03">Maret</option>
                            <option value="04">April</option>
                            <option value="05">Mei</option>
                            <option value="06">Juni</option>
                            <option value="07">Juli</option>
                            <option value="08">Agustus</option>
                            <option value="09">September</option>
                            <option value="10">Oktober</option>
                            <option value="11">November</option>
                            <option value="12">Desember</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Map / Akun:</label>
                <div class="col-md-4">
                    <input name="map" id="map" type="text" readonly="true"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Kode Jenis Pajak:</label>
                <div class="col-md-4">
                    <input name="kjs" id="kjs" type="text" readonly="true"/>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <label id="labelkodebilling" class="col-md-3 control-label">Kode Billing :</label>
                <div class="col-md-4">
                    <input name="kodebilling" id="kodebilling" type="text" readonly="true"/>
                </div>
            </div>
            <div id="labelnpwp" name="labelnamapptk" class="form-group">
                <label id="textnpwp" class="col-md-3 control-label">NPWP Penyetor:</label>
                <div class="col-md-4">
                    <input name="npwp" id="npwp" type="text" maxlength="15" readonly="true"/>
                </div>
            </div>
            <div id="labelnamanpwp" name="labelnamanpwp" class="form-group">
                <label id="textnamanpwp" class="col-md-3 control-label">Nama NPWP Penyetor :</label>
                <div class="col-md-4">
                    <input name="namanpwp" id="namanpwp" type="text" maxlength="100" readonly="true" />
                </div>
            </div>
            <div id="labelalamatnpwp" name="labelalamatnpwp" class="form-group">
                <label id="textalamatnpwp" class="col-md-3 control-label">Alamat NPWP Penyetor :</label>
                <div class="col-md-4">
                    <TEXTAREA name="alamatnpwp" id="alamatnpwp" cols="80" ROWS="3" maxlength="400" readonly="true"></TEXTAREA>
                </div>
            </div>
            <div class="form-group">
                <label id="labelnilaipajak" class="col-md-3 control-label">Nilai Pajak :</label>
                <div class="col-md-4">
                    <input name="nilaipajak" id="nilaipajak" type="text" readonly="true"/>
                </div>
            </div>


    <div class="form-actions fluid">
        <div id="divButton" class="col-md-offset-3 col-md-9" align="Right">
            <button type="button" id="btnBayar" class="btn blue" onclick='bayar()'>Bayar</button>
            <button type="button" id="btnKembali" class="btn blue" onclick='close()'>Kembali</button>

        </div>
    </div>
            </div>
</div>
</form:form>

<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sp2d/pajaktransfer/popupkonfirmasi.js"></script>
