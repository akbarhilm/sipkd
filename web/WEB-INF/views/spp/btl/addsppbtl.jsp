<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP BTL</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah  SPP BTL</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbtl"  id="refsppbtl" onsubmit="return cek()"  action="${pageContext.request.contextPath}/btl/prosessimpan" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BTL</div>       
        </div>
        <div class="portlet-body flip-scroll">
            <div class="col-md-7">
                <div class="form-group">
                    <label class="col-md-4 control-label">Tahun Anggaran:</label>
                    <div class="col-md-7">
                        <form:hidden path="id" id='id'    />
                        <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                        <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Bulan:</label>
                    <div class="col-md-7">
                        <form:input   path="bulan" id="bulan"    cssClass="required  entrybulan  ruleCekBulan"     size="8"    />mm/yyyy
                        <form:errors path="bulan" class="label label-important" />
                    </div>
                </div>
                 <%--   
                <div class="form-group">
                    <label class="col-md-4 control-label">Bulan Listing:</label>
                    <div class="col-md-7">
                        <form:input   path="bulanListing" id="bulanListing"    cssClass="required entrybulanlisting ruleCekBulan"  size="8"  onchange="cekbulanlisting(this.value)"  />mm/yyyy
                        <form:errors path="bulanListing" class="label label-important" />
                    </div>
                </div>
                        --%>
                    
                <div class="form-group">
                    <label class="col-md-4 control-label">SKPD:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="skpd.idSkpd" id='idskpd'   />
                            <form:input path="skpd.namaSkpd" id="skpd"  cssClass="required"   type="text" size="55" maxlength="80" readonly='true' value="${skpd.kodeSkpd}/${skpd.namaSkpd}"  />  
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
                    <label class="col-md-4 control-label">NIP Bendahara:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="nipPptk" id="nipPptk"  cssClass="required"   size="18" maxlength="18"   />  
                            <form:errors path="nipPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bendahara:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaPptk" id="namaPptk"  cssClass="required "   size="50" maxlength="50"   />  
                            <form:errors path="namaPptk"  cssClass="error"  />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label">Kode Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:hidden path="kodeBank" id='kodeBank'    />
                            <form:hidden path="idBank" id='idBank'    />
                            <form:input   path="kodeBankTransfer" id="kodeBankTransfer"  cssClass="required" onchange="getDataBankDki()"  size="10" maxlength="7"  readonly="true" />  &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listbankinduk?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a> 
                            <form:errors path="kodeBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nama Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:input   path="namaBankTransfer" id="namaBankTransfer"  cssClass="required"   size="60" maxlength="75"   readonly="true" />  
                            <form:errors path="namaBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">Nomor Rekening Bank:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!-- ruleCekCIF111 -->

                            <form:input   path="nomorRekBank" id="nomorRekBank"  cssClass="required ruleCekCIF111"   size="31" maxlength="30" onchange="getDataBankDki()" onkeypress="return isNumber(event)" />  &nbsp; <label class="control-label" id="labelCIF111" style="color: red">No Rekening Tidak Terdaftar</label>
                            <form:errors path="nomorRekBank"  cssClass="error"  />
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label">Uraian:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <form:textarea path="keterangan" id="uraian"  cssClass="required"    cols="58" rows="3"  />  
                            <form:errors path="keterangan"  cssClass="error"  />
                        </div>
                    </div>
                </div> 

                <div id="hiddenDiv2" class="form-group" style="visibility:hidden;">
                    <label class="col-md-4 control-label">Nama Penerima :</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!-- ruleCekNamaBtlPpkd -->
                            <form:input   path="namaPenerima" id="namaPenerima"  cssClass="required"   size="50" maxlength="55"   />  
                            <form:errors path="namaPenerima"  cssClass="error"  /> 
                        </div>
                    </div>
                </div>   

                <div id="hiddenDiv1" class="form-group" style="visibility:hidden;">
                    <label class="col-md-4 control-label">Nama Pimpinan:</label>
                    <div class="col-md-7">
                        <div class="input-group">
                            <!--  -->
                            <form:input   path="namaYayasan" id="namaYayasan" cssClass="required "  size="45" maxlength="50" onchange="ceknamapenerima()"  />  &nbsp; <label class="control-label" id="labelCekNama" style="color: red">Nama Harus Sesuai dengan Nama dari Data Bank DKI</label>
                            <form:errors path="namaYayasan"  cssClass="error"  /> 
                            
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


                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>

            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Simpan </button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>

        </div>
    </div>
    <div class="portlet">

        <input type="hidden" id="banyakrinci" name="banyakrinci"  />
        <table id="spprincitable" class="table table-striped table-bordered table-condensed table-hover " >
            <thead>
                <tr>
                    <th rowspan="2">No</th>
                    <th rowspan="2">Nomor SPD</th>
                    <th rowspan="2">Kode Akun</th>
                    <th rowspan="2">Nama Akun</th>
                    <th rowspan="2">Nilai SPD</th> 
                    <th rowspan="2">SPP SEBELUM</th>
                    <th rowspan="2">SPP SISA</th>
                    <th rowspan="2">Nilai SPP</th>  

                </tr>
                <tr> 
                    <th>Pilih</th> 


                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th colspan="7" style="text-align:right">Total:</th>
                    <!-- <th>
                         <input type='text' id="totalspd"  name="totalspd" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                     </th> -->
                    <th>
                        <input type='text' id="totalspp"   name="totalspp" readonly="true"  class="inputmoney"  style='border:none;margin:0;width:99%;'    />
                    </th>
                    <th></th>
                </tr>
            </tfoot>
            <tbody id="spprincitablebody" >
            </tbody>             
        </table> 
    </div> 
    <script type="text/javascript">
        function idskpdCheck() {
            var idskpd = document.getElementById("idskpd").value;
            // alert(idskpd);
            if (idskpd == "761" || idskpd == "1234") {
                document.getElementById("hiddenDiv1").style.visibility = "visible";
                document.getElementById("hiddenDiv2").style.visibility = "visible";
                $('#namaPenerima').attr("disabled", false);
                $('#namaYayasan').attr("disabled", false);

            } else
            {
                //window.location = "http://google.com";
                document.getElementById("hiddenDiv1").style.visibility = "hidden";
                document.getElementById("hiddenDiv2").style.visibility = "hidden";
                $('#namaPenerima').attr("disabled", true);
                $('#namaYayasan').attr("disabled", true);

            }

        }

        function isNumber(evt) {
            evt = (evt) ? evt : window.event;
            var charCode = (evt.which) ? evt.which : evt.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            return true;
        }

    </script>



</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbtl/addsppbtl.js"></script>  