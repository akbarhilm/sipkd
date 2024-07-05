<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <form:form  method="post" commandName="spdBTLMaster"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bast/updatebast" class="form-horizontal">         

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">  
                        <form:hidden path="kontrak.idKontrak" id="idKontrak" value="${kontrak.idKontrak}"  cssClass="required" size="10" maxlength="180" readonly='true'  />
                        <form:hidden path="idBast" id='idBast'      />  
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
                        <form:hidden path="idSpd" id="idSpd"/>
                        <form:hidden path="idkegiatan" id='idkegiatan' readonly='true' value="${idkegiatan}" onchange="gridakun();getbanyakakun();getsisabast()"  />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan"  value="${kegiatan.namaKegiatan}" cssClass="required"   type="text" size="80" readonly='true'  />
                        &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/bast/listpopupkontrak/?target='_top'" title="Pilih Kegiatan"  ><i class="icon-zoom-in"></i> Pilih</a> 

                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Kontrak:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="hidden" name="bastSebelum" id="bastSebelum" />
                        <form:input path="nilaiKontrak" id="nilaiKontrak"  cssClass="required"   type="text" size="30" readonly='true'  />

                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai Sisa BAST:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <input type="text" name="sisaBast" id="sisaBast" readonly='true'/>
                    </div>
                </div>
            </div>   

            <div class="form-group">
                <label class="col-md-3 control-label">Nomor BAST:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noBast" id="noBast"  readonly="true"      size="10" maxlength="50"  />  
                        <form:errors path="noBast" class="error" />
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">Tanggal BAST :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input type="text" name="tglBast" path="tglBast" id="tglBast" class="required form-control form-control-inline input-small date-picker2 entrytanggal2 valid" size="14"  />
                        <form:errors path="tglBast" class="error" />
                    </div>
                </div>  
            </div>     



            <div class="form-group">
                <label class="col-md-3 control-label">Nama PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPptk" id="namaPptk"   cssClass="required"     size="55" maxlength="50"   />  
                        <form:errors path="namaPptk" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NIP PPTK :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPptk" id="nipPptk"    cssClass="required"    size="20" maxlength="18"   />  
                        <form:errors path="nipPptk" class="error" />
                    </div>
                </div>  
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaPemeriksaBarang" id="namaPemeriksaBarang"   cssClass="required"     size="55" maxlength="50"   />  
                        <form:errors path="namaPemeriksaBarang" class="error" />
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NIP Pemeriksa Barang :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nipPemeriksaBarang" id="nipPemeriksaBarang"   cssClass="required"     size="20" maxlength="18"   />  
                        <form:errors path="nipPemeriksaBarang" class="error" />
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Uang Muka :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" name="statusUM" id="statusUM" />
                        <form:hidden path="statusUangMuka" id="statusUangMuka" />
                        <input type="checkbox" name="cbUMK" id="cbUMK" onclick="setUMK()" />
                       
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Keterangan :</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketBast" id="ketBast"    size="55" maxlength="250"   />  
                        <form:errors path="ketBast" class="error" />
                    </div>
                </div>  
            </div>   

            <div >
                <input type="hidden" name="banyakakun" id="banyakakun"  value="${fn:length(listbast)}" />
                <table id="akunpopup" class="table table-striped table-bordered table-condensed table-hover " >
                    <thead>
                        <tr>
                            <th>No</th>
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
                            <th colspan="5" style="text-align:right">Total:</th>
                            <th>
                                <input type='text' id="totalbast"  name="totalbast" readonly="true"     style='border:none;margin:0;width:99%; text-align: right;'    />
                            </th>

                        </tr>
                    </tfoot>

                    <tbody  >
                        <%/*  <c:forEach var="bast" items="${listbast}" varStatus="idx">
                             <tr>
                             <td>${idx.index+1}  <input type='hidden' name="idBast${idx.index+1}" id='idBast${idx.index+1}' value='${bast.idBast}' /> </td>
                             <td>${bast.akun.namaAkun}</td>
                             <td><input type='text'   id='nilaiprestasi${idx.index+1}'    name='nilaiprestasi${idx.index+1}'    value='${bast.nilaiPrestasi}'  class='inputmoney sppnilai'      /> 
                             </td> 
                             <td>
                             <input type='hidden' id='namaAkun${idx.index+1}' value='${idx.index+1}' /> 
                             <input type='hidden' id='idAkun${idx.index+1}'  name='idAkun${idx.index+1}' value='${bast.akun.idAkun}' /> 
                             <input type='text'   id='nilaibast${idx.index+1}'    name='nilaibast${idx.index+1}'    value='${bast.nilaiBast}'   class='inputmoney sppnilai'       /> 
                             </td>
                             <td> 
                             <input type='text'   id='nilaispd{idx.index+1}'    name='nilaispd{idx.index+1}'    value='${bast.nilaiSpd}'   class='inputmoney sppnilai'       /> 
                             </td>
                             <td>            
                             <input type='text'   id='nilaianggaran{idx.index+1}'    name='nilaianggaran{idx.index+1}'    value='${bast.nilaiAnggaran}'   class='inputmoney sppnilai'       /> 
                             </td>
                             </tr>
                             </c:forEach> */%>
                    </tbody>
                </table> 
            </div> 
            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bast" >Kembali</a>
                    <!--  <button type="button" class="btn blue" onclick='cek()'>CEK</button> -->
                </div>
            </div>
        </form:form>
    </div>
</div> 
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/updatebast.js"></script>  