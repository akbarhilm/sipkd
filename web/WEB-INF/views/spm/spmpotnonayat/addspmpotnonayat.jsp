<%--
    Document   : addspmpotayat
    Created on : Nov 24, 2014, 9:51:28 AM
    Author     : Xalamaster
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPM</a>
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">SPM Potongan Ayat</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>SPM Potogan Non Ayat - BELANJA LANGSUNG LS</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
        <form class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="tahunAnggaran" size="5"  class="m-wrap medium inputnumber" readonly="true" value="${tahunAnggaran}"  onchange="gridspmpotayat()" />
                        <input type="hidden" id="idskpd"  value="${skpd.idSkpd}" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" id="skpd" name="skpd"  value="${skpd.kodeSkpd}/${skpd.namaSkpd}" class="m-wrap large" size="50" readonly="true" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">NO.SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input id="sspm" name="sspm" value="${nospm}" size="7" readonly="true"   class="m-wrap large"  />
                        <input id="nospm" type="hidden" name="nospm" value="${idspm}"/>
                        &nbsp; &nbsp;<!--a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/spmpotayat/spmlistpopup" title="Pilih SPM"  ><i class="icon-zoom-in"></i> Pilih</a-->
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">TGL.SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input id="sispm" name="sispm" value="${tglspm}" size="10" readonly="true" Class=""   />
                        <input type="hidden" id="ssspm" name="ssspm" value="${tglspm}" size="20" readonly="true" Class=""   />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Total SPM</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="text" readonly="true" id="totspm" name="totspm"  value="" class="inputmoney" onChange="" size="25" readonly="true" />
                        <input type="hidden" id="totspmhide" name="totspmhide" value="">
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>
<div class="portlet box" id="gridpotongandiv" >
    <form id="formpagusppgup">
        <div class="portlet-title">

        </div>
        <div class="portlet-body">
            <table id="btlspdtable" class="table table-striped table-bordered table-condensed table-hover " >
                <thead>
                    <tr>
                        <th width="85">No</th>
                        <th>Potongan</th>
                        <th width="300"></th>
                        <th width="150">Kode Akun Pajak</th>
                        <th width="200">Persentase</th>
                        <th width="100">Kode Setoran Pajak</th>
                        <th width="200">Nilai</th>
                    </tr>
                </thead>
                <tbody id="btlspdtablebody" >
                    <tr>
                        <td class="center"> 1</td>
                        <td>
                            <div id="tampildataholder1" onchange="setvpot1()"></div>
                        </td>
                        <td></td>
                        <td class="center">
                            411211
                            <input type="hidden" id="cakunpajak1" value="411211" >
                        </td>
                        <!--   <td class="center">10%</td> -->
                        <td  class="center">
                            <input type="checkbox"  id= "cekppn10" name="cekppn10" value="" class="checked" onchange="hitungPPN10()">
                            <!-- tambahan dari permintaan pak gunawan di wa tgl 09-12-2016-->
                            <!-- awal tambahan -->
                            <select id="persen1" name="persen1" onchange="hitungPPN10()"  >
                                <option value=0>Pilih</option>
                                <option value=10>10%</option>
                                <option value=1>1%</option>
                            </select>
                            <!-- akhir tambahan-->
                        </td>
                        <td class="center">
                            920
                            <input type="hidden" id="csetorpajak1" value="920" >
                        </td>
                        <td>
                            <input type="text" id="vpot1" name="vpot1" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot1" name="cpot1"  value="">
                            <input type="hidden" id="status1" name="status1">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 2</td>
                        <td>
                            <div id="tampildataholder2" onchange="setvpot2()"><div id="tampildata2"></div></div>
                        </td>
                        <td></td>
                        <td class="center">
                            411122
                            <input type="hidden" id="cakunpajak2" value="411122" >
                        </td>
                        <!--   <td class="center">1.5%</td> -->
                        <td  class="center">
                            <input type="checkbox"  id= "cekpphps22" name="cekpphps22" value="" class="checked" onchange="hitungPPHPS22()">
                            <select id="cekpphps22status" name="cekpphps22status" onchange="hitungPPHPS22()"  >
                                <option value=1>PPN</option>
                                <option value=0>NON PPN</option>
                            </select>
                        </td>
                        <td class="center">
                            920
                            <input type="hidden" id="csetorpajak2" value="920" >
                        </td>
                        <td>
                            <input type="text" id="vpot2" name="vpot2" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot2" name="cpot2"  value="">
                            <input type="hidden" id="status2" name="status2">
                        </td>
                    </tr>
                    <!--
                    <tr>
                        <td class="center"> 3</td>
                        <td>
                            <div id="tampildataholder3"><div id="tampildata3"></div></div>
                        </td>
                        <td class="center">
                            <input type="text" id="asuransi3" name="asuransi3" size="15" value="" class="inputnumber" onkeyup ="hitungasuransi()" onkeydown="validateNumber(this.input)">  x Rp 20.000; </>
                        </td>
                        <td>
                            <input type="text" id="vpot3" name="vpot3" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot3" name="cpot3"  value="">
                            <input type="hidden" id="status3" name="status3">
                        </td>
                    </tr>
                    -->
                    <!-- TAMBAH ASURANSI (LAGI) 8 MARET 2016  -->
                    <tr>
                        <td class="center"> 3</td>
                        <td>
                            <div id="tampildataholder3"><div id="tampildata3"></div></div>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot3" name="vpot3" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot3" name="cpot3"  value="">
                            <input type="hidden" id="status3" name="status3">
                        </td>
                    </tr>

                    <tr>
                        <td class="center"> 4</td>
                        <td>
                            <div id="tampildataholder4"><div id="tampildata4"></div></div>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot4" name="vpot4" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot4" name="cpot4"  value="">
                            <input type="hidden" id="status4" name="status4">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 5</td>
                        <td>
                            <div id="tampildataholder5"><div id="tampildata5"></div></div>
                        </td>
                        <td></td>
                        <td></td>
                        <td  class="center">
                            <input type="checkbox"  id= "cekjamsostek" name="cekjamsostek" value="" class="checked" onchange="hitungJamostek()">
                        </td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot5" name="vpot5" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot5" name="cpot5"  value="">
                            <input type="hidden" id="status5" name="status5">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 6</td>
                        <td>
                            <div id="tampildataholder6"><div id="tampildata6"></div></div>
                        </td>
                        <td></td>
                        <td class="center">
                            411124
                            <input type="hidden" id="cakunpajak6" value="411124" >
                        </td>
                        <td class="center">
                            <select id="cekpersen6status" name="cekpersen6status" onchange="hitung6()"  >
                                <option value=1>PPN</option>
                                <option value=0>NON PPN</option>
                            </select>
                            <select id="persen6" name="persen6" onChange="hitung6()"  >
                                <option value="0">Pilih</option>
                                <option value="2">2%</option>
                                <%-- permintaan pak gunawan wa tanggal 09-12-2016 yang digunakan hanya 2% saja
                                <option value="0.5">0.5%</option>
                                <option value="1">1%</option>
                                <option value="1.5">1.5%</option>

                                <option value="4">4%</option>
                                <option value="8">8%</option>
                                --%>
                            </select>
                        </td>
                        <td class="center">
                            <select id="csetorpajak6" name="csetorpajak6" >
                                <option value=100>100</option>
                                <option value=104 selected="true">104</option>
                            </select>
                        </td>
                        <td>
                            <input type="text" id="vpot6" name="vpot6" class="inputmoney" readonly="true"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot6" name="cpot6"  value="">
                            <input type="hidden" id="status6" name="status6">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 7</td>
                        <td>
                            <div id="tampildataholder7"><div id="tampildata7"></div></div>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot7" name="vpot7" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot7" name="cpot7"  value="">
                            <input type="hidden" id="status7" name="status7">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 8</td>
                        <td>
                            <div id="tampildataholder8"><div id="tampildata8"></div></div>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="text" id="vpot8" name="vpot8" class="inputmoney edit2"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot8" name="cpot8"  value="">
                            <input type="hidden" id="status8" name="status8">
                        </td>
                    </tr>
                    <tr>
                        <td class="center"> 9</td>
                        <td>
                            <div id="tampildataholder9"><div id="tampildata9"></div></div>
                        </td>
                        <td>
                            <select id="cpns" name="cpns" onchange="setgolpeg(), setSetorPajak9(this.value)">
                                <option value=0 selected="true">NON PNS</option>
                                <option value=1>PNS</option>
                            </select>
                            <select id="cpnsgol" name="cpnsgol" onchange="hitungpph21()">
                                <option value=0 selected="true">Pilih</option>
                                <option value=1>Golongan-I</option>
                                <option value=2>Golongan-II</option>
                                <option value=3>Golongan-III</option>
                                <option value=4>Golongan-IV</option>
                            </select>
                            <select id="cpeg" name="cpeg" onchange="hitungpph21()">
                                <option value=0 selected="true">Pilih</option>
                                <option value=1>Pegawai Tidak Tetap</option>
                                <option value=2>Bukan Pegawai</option>
                                <option value=3>Peserta Kegiatan</option>
                            </select>
                        </td>
                        <td class="center">
                            411121
                            <input type="hidden" id="cakunpajak9" value="411121" >
                        </td>
                        <td></td>
                        <td class="center">
							<div id="tampilancsetorpajak9"  ></div>
                            <input type="hidden" id="csetorpajak9" >
							<!--
                            <select id="csetorpajak9" name="csetorpajak9" >
                                <option value=100 selected="true">100</option>
                                <option value=402>402</option>
                            </select>
							-->
                        </td>
                        <td>
                            <input type="text" id="vpot9" name="vpot9" class="inputmoney"  value="" onkeyup="cekmoney()">
                            <input type="hidden" id="cpot9" name="cpot9"  value="">
                            <input type="hidden" id="status9" name="status9">
                        </td>
                    </tr>
                    <tr>
                        <td class="center">10</td>
                        <td>
                            <div id="tampildataholder10"  value="">
                                <div id="tampildata10"  value=""></div>
                            </div>
                        </td>
                        <td></td>
                        <td class="center">
                            411128
                            <input type="hidden" id="cakunpajak10" value="411128" >
                        </td>
                        <td class="center">
                            <select id="cekpersen10" name="cekpersen10" onchange="hitung10()"  >
                                <option value=1>PPN</option>
                                <option value=0>NON PPN</option>
                            </select>
                            <select id="persen10" name="persen10" onChange="hitung10(), setSetorPajak10(this.value)"  >
                                <option value="0">Pilih</option>
                                <option value="0.5">0.5%</option>
                                <option value="2">2%</option>
                                <option value="3">3%</option>
                                <option value="4">4%</option>
                                <option value="5">5%</option>
                                <option value="6">6%</option>
                                <option value="10">10%</option>
                            </select>

                        </td>
                        <td class="center">
                            <div id="tampilancsetorpajak10"  ></div>
                            <input type="hidden" id="csetorpajak10" >
                        </td>
                        <td>
                            <input type="text" id="vpot10" name="vpot10" class="inputmoney edit2" readonly="true"  onkeyup="cekmoney()">
                            <input type="hidden" id="cpot10" name="cpot10">
                            <input type="hidden" id="status10" name="status10">
                        </td>
                    </tr>

                    <tr>
                        <td class="center">11</td>
                        <td>
                            <div id="tampildataholder11"  value="">
                                <div id="tampildata11"  value=""></div>
                            </div>
                        </td>
                        <td></td>
                        <td class="center">
                            411128
                            <input type="hidden" id="cakunpajak11" value="411128" >
                        </td>
                        <td class="center">
                            <select id="cekpersen11" name="cekpersen11" onchange="hitung11()"  >
                                <option value=1>PPN</option>
                                <option value=0>NON PPN</option>
                            </select>
                            <select id="persen11" name="persen11" onChange="hitung11(), setSetorPajak11(this.value)"  >
                                <option value="0">Pilih</option>
                                <option value="1.2">1.2%</option>
                                <option value="1.8">1.8%</option>
                                <option value="2.64">2.64%</option>

                            </select>

                        </td>
                        <td class="center">
                            <div id="tampilancsetorpajak11"  ></div>
                            <input type="hidden" id="csetorpajak11" >
                        </td>
                        <td>
                            <input type="text" id="vpot11" name="vpot11" class="inputmoney edit2" readonly="true"  onkeyup="cekmoney()">
                            <input type="hidden" id="cpot11" name="cpot11">
                            <input type="hidden" id="status11" name="status11">
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </form>
</div>
<div class="form-actions fluid" id="gridbuttondiv">
    <div class="col-md-offset-3 col-md-9" align="Right">
        <button type="button" id="btnSimpan" class="btn blue" onclick='ceksubmitnilai()'>Simpan</button>
        <!-- <button type="button" class="btn blue" onclick='cek( )'>Cek</button> -->
        <a  href="${pageContext.request.contextPath}/spmblls/indexspmblls" id="btnKembali" class="btn blue" >Kembali</a>
    </div>
</div>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/spmpotayat/spmpotnonayat.js"></script>
<script type="text/javascript">

</script>
